package dev.lekim.githubadmin.ui.navigator

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    // Route for nested graph
    @Serializable object Home

    @Serializable
    data object UserList

    @Serializable
    data class UserDetail(val username: String)
}
