package dev.lekim.githubadmin.ui.user.userDetail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lekim.githubadmin.data.user.User
import dev.lekim.githubadmin.data.user.userDetail.UserDetailRepository
import dev.lekim.githubadmin.data.shared.*
import dev.lekim.githubadmin.param.user.UserGetParam
import dev.lekim.githubadmin.ui.shared.LoadState
import dev.lekim.githubadmin.util.shared.RemoteException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: UserDetailRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(UserDetailUiState())
    val uiState: StateFlow<UserDetailUiState> = _uiState.asStateFlow()

    suspend fun getUser(param: UserGetParam) {
        _uiState.update {
            it.copy(
                loadState = LoadState.Loading
            )
        }
        delay(300L)

        try {
            repository.getUser(param).let { result ->
                _uiState.update {
                    it.copy(
                        loadState = LoadState.Success,
                        user = result.data
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