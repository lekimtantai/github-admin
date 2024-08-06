package dev.lekim.githubadmin.ui.user.userList

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lekim.githubadmin.data.user.userList.UserListRepository
import dev.lekim.githubadmin.data.shared.*
import dev.lekim.githubadmin.param.user.UserGetListParam
import dev.lekim.githubadmin.ui.shared.LoadState
import dev.lekim.githubadmin.util.shared.RemoteException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserListRepository
) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UserListUiState> = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    private val _getListParam = MutableStateFlow(UserGetListParam(page = 1, perPage = 20))

    init {
        viewModelScope.launch {
            getUsers(_getListParam.value)
        }
    }

    fun onRefresh() {
        _getListParam.update {
            it.copy(
                page = 1,
                perPage = 20
            )
        }

        _uiState.update {
            it.copy(
                loadState = LoadState.Idle,
                page = _getListParam.value.page,
                perPage = _getListParam.value.perPage,
                users = emptyList()
            )
        }

        viewModelScope.launch {
            getUsers(_getListParam.value)
        }
    }

    fun increasePageNumber() {
        require(uiState.value.page == _getListParam.value.page) {
            "The page number must be equal to getListParam page number."
        }

        _getListParam.update {
            it.copy(page = uiState.value.page + 1)
        }

        viewModelScope.launch {
            getUsers(_getListParam.value)
        }
    }

    private suspend fun getUsers(param: UserGetListParam) {
        try {
            // Local source part
            if (_uiState.value.loadState.isInit) {
                _uiState.update {
                    it.copy(loadState = LoadState.Loading)
                }

                val resultFromLocal = repository.getUsersFromLocal(param)

                if (resultFromLocal.items.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            loadState = LoadState.Success,
                            page = resultFromLocal.page,
                            perPage = resultFromLocal.perPage,
                            users = resultFromLocal.items,
                        )
                    }
                    return
                }
            }

            // Remote Source part
            _uiState.update {
                if (param.page == 1) {
                    it.copy(loadState = LoadState.Loading)
                } else {
                    it.copy(loadState = LoadState.LoadMore)
                }
            }

            delay(1000L)

            repository.getUsersFromRemote(param).let { result ->
                _uiState.update {
                    it.copy(
                        loadState = LoadState.Success,
                        page = result.page,
                        perPage = result.perPage,
                        users = it.users + result.items,
                    )
                }
            }
        } catch (e: RemoteException) {
            _uiState.update {
                it.copy(
                    loadState = LoadState.Failure(error = e.error)
                )
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    loadState = LoadState.Failure(error = ErrorMessage.toMessage(e))
                )
            }
        }
    }

}