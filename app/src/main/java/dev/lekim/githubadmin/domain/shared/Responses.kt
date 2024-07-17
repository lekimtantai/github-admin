package dev.lekim.githubadmin.domain.shared

import kotlinx.serialization.*

sealed interface BaseResponse<T>

sealed interface StandardResponse<T> : BaseResponse<T>
sealed interface ListingResponse<T> : BaseResponse<T>
sealed interface PagingResponse<T> : BaseResponse<T>

@Serializable
data class StandardSuccess<T>(
    val data: T
) : StandardResponse<T>

@Serializable
data class ListingSuccess<T>(
    val items: List<T>
) : ListingResponse<T>

@Serializable
data class PagingSuccess<T>(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val items: List<T>,
) : PagingResponse<T>

@Serializable
data class ResponseFailure<T>(
    val message: String,
    @SerialName("documentation_url")
    val documentationUrl: String,
    val status: String,
) : StandardResponse<T>, ListingResponse<T>, PagingResponse<T>