package dev.lekim.githubadmin.data.users.dataSources

import dev.lekim.githubadmin.domain.shared.*
import dev.lekim.githubadmin.domain.users.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*

class UserRemoteDataSource : UserDataSource {
    private val client = RemoteConfig.client

    override suspend fun getUsers(param: UserGetListParam): ListingResponse<UserItem> {
        try {
            val response = client.use {
                it.get {
                    url {
                        parameters.apply {
                            append(param.page.javaClass.name, param.page.toString())
                            append(param.perPage.javaClass.name, param.perPage.toString())
                        }
                    }
                }
            }

            return if (response.status.isSuccess()) {
                response.body<ListingSuccess<UserItem>>()
            } else {
                response.body<ResponseFailure<UserItem>>()
            }

        } catch (e: Exception) {
            println(e.message)
            return ListingSuccess(items = emptyList())
        }
    }

    override suspend fun getUser(param: UserGetParam): StandardResponse<User> {
        try {
            val response = client.use {
                it.get {
                    url {
                        parameters.append(param.username.javaClass.name, param.username)
                    }
                }
            }

            return if (response.status.isSuccess()) {
                response.body<StandardSuccess<User>>()
            } else {
                response.body<ResponseFailure<User>>()
            }
        } catch (e: Exception) {
            println(e.message)
            throw e
        }
    }
}