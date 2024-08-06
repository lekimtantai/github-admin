package dev.lekim.githubadmin.data.user.userList.local

import android.content.res.Resources
import dev.lekim.githubadmin.R
import dev.lekim.githubadmin.data.user.UserItem
import dev.lekim.githubadmin.param.user.UserGetListParam
import dev.lekim.githubadmin.util.shared.LocalException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserListLocal @Inject constructor(private val userItemDao: UserItemDao) {
    suspend fun getUsers(param: UserGetListParam): List<UserItem> =
        // Delegate to IO thread to handle disk read
        withContext(Dispatchers.IO) {
            userItemDao.getUsers().catch {
                throw LocalException(
                    errorMsg = Resources.getSystem().getString(R.string.db_error_msg)
                )
            }.first()
        }

    suspend fun insertUsers(users: List<UserItem>) =
        // Delegate to IO thread to handle disk write
        withContext(Dispatchers.IO) {
            userItemDao.insertUsers(users = users)
        }
}