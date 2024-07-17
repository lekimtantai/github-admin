package dev.lekim.githubadmin.domain.shared

import kotlinx.serialization.Serializable

sealed class Route {
    // Route for nested graph
    @Serializable object Home

    @Serializable
    data object UserList

    @Serializable
    data class UserDetail(val username: String)
}
