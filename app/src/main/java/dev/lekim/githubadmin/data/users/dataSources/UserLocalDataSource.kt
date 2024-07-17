package dev.lekim.githubadmin.data.users.dataSources

import dev.lekim.githubadmin.domain.shared.*
import dev.lekim.githubadmin.domain.users.*

class UserLocalDataSource : UserDataSource {
    override suspend fun getUsers(param: UserGetListParam): ListingResponse<UserItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(param: UserGetParam): StandardResponse<User> {
        TODO("Not yet implemented")
    }
}