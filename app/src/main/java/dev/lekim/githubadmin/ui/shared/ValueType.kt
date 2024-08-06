package dev.lekim.githubadmin.ui.shared

import dev.lekim.githubadmin.data.shared.ErrorMessage

sealed interface LoadState {
    data object Init : LoadState
    data object Idle : LoadState
    data object Success : LoadState
    data object Loading : LoadState
    data object LoadMore : LoadState
    class Failure(val error: ErrorMessage) : LoadState

    val isInit: Boolean get() = this is Init
    val isIdle: Boolean get() = this is Idle
    val isSuccess: Boolean get() = this is Success
    val isLoading: Boolean get() = this is Loading
    val isLoadMore: Boolean get() = this is LoadMore
    val isFailure: Boolean get() = this is Failure
}

// Others