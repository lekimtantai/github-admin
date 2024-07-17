package dev.lekim.githubadmin.domain.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItem(
    val id: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
)

@Serializable
data class User(
    val id: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    val location: String,
    val followers: Int,
    val following: Int,
)
