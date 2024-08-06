package dev.lekim.githubadmin.ui.user.userDetail

import androidx.compose.runtime.Stable
import dev.lekim.githubadmin.data.user.User
import dev.lekim.githubadmin.ui.shared.LoadState

@Stable
data class UserDetailUiState(
    val loadState: LoadState = LoadState.Init,
    val user: User? = null
)