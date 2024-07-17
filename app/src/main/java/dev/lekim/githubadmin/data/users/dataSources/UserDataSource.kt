package dev.lekim.githubadmin.data.users.dataSources

import dev.lekim.githubadmin.domain.shared.*
import dev.lekim.githubadmin.domain.users.*

interface UserDataSource {
    suspend fun getUsers(param: UserGetListParam): ListingResponse<UserItem>

    suspend fun getUser(param: UserGetParam): StandardResponse<User>
}