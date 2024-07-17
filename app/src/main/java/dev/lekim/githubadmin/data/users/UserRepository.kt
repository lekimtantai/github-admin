package dev.lekim.githubadmin.data.users

import dev.lekim.githubadmin.data.users.dataSources.*
import dev.lekim.githubadmin.domain.shared.*
import dev.lekim.githubadmin.domain.users.*

class UserRepository(
    private val local: UserLocalDataSource,
    private val remote: UserRemoteDataSource
) {
    suspend fun getUsers(param: UserGetListParam): ListingResponse<UserItem> {
        return remote.getUsers(param)
    }

    suspend fun getUser(param: UserGetParam): StandardResponse<User> {
        return remote.getUser(param)
    }

}