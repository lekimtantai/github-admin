package dev.lekim.githubadmin.data.user.userDetail.local

import dev.lekim.githubadmin.data.shared.StandardResponse
import dev.lekim.githubadmin.data.user.User
import dev.lekim.githubadmin.param.user.UserGetParam
import kotlinx.coroutines.*

class UserDetailLocal {
    suspend fun getUser(param: UserGetParam): StandardResponse<User> =
        // Delegate to IO thread to handle disk read
        withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
}