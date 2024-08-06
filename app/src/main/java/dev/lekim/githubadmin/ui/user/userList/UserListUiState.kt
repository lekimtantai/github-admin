package dev.lekim.githubadmin.ui.user.userList

import androidx.compose.runtime.Stable
import dev.lekim.githubadmin.data.user.UserItem
import dev.lekim.githubadmin.ui.shared.LoadState

@Stable
data class UserListUiState(
    val loadState: LoadState = LoadState.Init,
    val page: Int = 1,
    val perPage: Int = 20,
    val users: List<UserItem> = emptyList()
)