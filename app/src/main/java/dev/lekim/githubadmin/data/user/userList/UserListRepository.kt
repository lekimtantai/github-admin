package dev.lekim.githubadmin.data.user.userList

import android.util.Log
import dev.lekim.githubadmin.data.user.userList.local.UserListLocal
import dev.lekim.githubadmin.data.user.userList.remote.UserListRemote
import dev.lekim.githubadmin.data.shared.PagingResponse
import dev.lekim.githubadmin.data.user.UserItem
import dev.lekim.githubadmin.param.user.UserGetListParam
import dev.lekim.githubadmin.util.shared.LogTag
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val local: UserListLocal,
    private val remote: UserListRemote,
) {
    suspend fun getUsersFromRemote(param: UserGetListParam): PagingResponse<UserItem> {
        val result = remote.getUsers(param)
        Log.d(LogTag.USER_LIST_SOURCE, "UsersFromRemote Get Success")

        if (param.page == 1) {
            local.insertUsers(result.items)
            Log.d(LogTag.USER_LIST_SOURCE, "UsersFromRemote Insert Success")
        }

        return result
    }

    suspend fun getUsersFromLocal(param: UserGetListParam): PagingResponse<UserItem> {
        val users = local.getUsers(param)
        Log.d(LogTag.USER_LIST_SOURCE, "UsersFromLocal Get Success")

        return PagingResponse(
            page = 1,
            perPage = 20,
            items = users
        )
    }
}