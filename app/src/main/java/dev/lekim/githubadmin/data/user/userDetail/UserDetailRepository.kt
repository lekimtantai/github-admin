package dev.lekim.githubadmin.data.user.userDetail

import dev.lekim.githubadmin.data.user.User
import dev.lekim.githubadmin.data.shared.StandardResponse
import dev.lekim.githubadmin.data.user.userDetail.remote.UserDetailRemote
import dev.lekim.githubadmin.param.user.UserGetParam
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
//    private val local: UserDetailLocal,
    private val remote: UserDetailRemote,
) {
    suspend fun getUser(param: UserGetParam): StandardResponse<User> {
        return remote.getUser(param)
    }
}