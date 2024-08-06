package dev.lekim.githubadmin.data.user.userDetail.remote

import dev.lekim.githubadmin.data.shared.*
import dev.lekim.githubadmin.data.user.User
import dev.lekim.githubadmin.param.user.UserGetParam
import dev.lekim.githubadmin.util.shared.RemoteException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*
import javax.inject.Inject

class UserDetailRemote @Inject constructor(private val client: HttpClient) {
    suspend fun getUser(param: UserGetParam): StandardResponse<User> =
        // Delegate to IO thread to handle network load
        withContext(Dispatchers.IO) {
            val response = client.get {
                url {
                    appendPathSegments("users", param.username)
                }
            }

            if (response.status.isSuccess()) {
                val tempData = response.body<User>()
                // Simulate to receive result as base response
                StandardResponse(data = tempData)
            } else {
                throw RemoteException(error = response.body<ErrorMessage>())
            }
        }
}