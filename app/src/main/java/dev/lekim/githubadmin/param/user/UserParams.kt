package dev.lekim.githubadmin.param.user

import androidx.compose.runtime.Stable
import kotlinx.serialization.*

@Serializable
@Stable
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