package dev.lekim.githubadmin.data.user

import androidx.room.*
import kotlinx.serialization.*

@Serializable
data class User(
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    val location: String?,
    val followers: Int?,
    val following: Int?,
    val blog: String?
)

@Entity(tableName = "UserItems")
@Serializable
data class UserItem(
    @PrimaryKey
    val id: Int,
    val login: String,
    @ColumnInfo(name = "avatar_url")
    @SerialName("avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "html_url")
    @SerialName("html_url")
    val htmlUrl: String
)
