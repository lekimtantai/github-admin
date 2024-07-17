package dev.lekim.githubadmin.domain.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class UserGetListParam(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int
) {
    init {
        require(page > -1) { "The page number must not be negative." }
        require(perPage > 0) { "The perPage number not be less than zero." }
    }
}

@Serializable
data class UserGetParam(
    val username: String
)