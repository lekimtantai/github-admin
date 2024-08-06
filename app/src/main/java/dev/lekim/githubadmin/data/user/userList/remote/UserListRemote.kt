package dev.lekim.githubadmin.data.user.userList.remote

import dev.lekim.githubadmin.data.shared.*
import dev.lekim.githubadmin.data.user.UserItem
import dev.lekim.githubadmin.param.user.UserGetListParam
import dev.lekim.githubadmin.util.shared.RemoteException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*
import javax.inject.Inject

class UserListRemote @Inject constructor(private val client: HttpClient) {
    suspend fun getUsers(param: UserGetListParam): PagingResponse<UserItem> =
        // Delegate to IO thread to handle network load
        withContext(Dispatchers.IO) {
            val response = client.get {
                url {
                    appendPathSegments("users")
                    parameters.apply {
                        append(UserGetListParam::page.name, param.page.toString())
//                            append(UserGetListParam::perPage.name, param.perPage.toString())
                        append("per_page", param.perPage.toString())
                    }
                }
            }

            if (response.status.isSuccess()) {
                val tempData = response.body<List<UserItem>>()
                // Simulate to receive result as base response
                PagingResponse(
                    page = param.page,
                    perPage = param.perPage,
                    items = tempData
                )
            } else {
                throw RemoteException(error = response.body<ErrorMessage>())
            }
        }
}