package dev.lekim.githubadmin.data.shared

import kotlinx.serialization.*

@Serializable
data class StandardResponse<T>(
    val data: T
)

@Serializable
data class ListingResponse<T>(
    val items: List<T>
)

@Serializable
data class PagingResponse<T>(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    val items: List<T>,
)

@Serializable
data class ErrorMessage(
    val message: String,
    @SerialName("documentation_url")
    val documentationUrl: String? = null,
    val status: String? = null,
) {
    companion object {
        fun toMessage(exception: Exception): ErrorMessage = ErrorMessage(
            message = exception.message ?: "I don't known this error",
        )
    }
}