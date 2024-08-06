package dev.lekim.githubadmin.data.shared.remote

import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.lekim.githubadmin.BuildConfig
import dev.lekim.githubadmin.data.user.userDetail.remote.UserDetailRemote
import dev.lekim.githubadmin.data.user.userList.remote.UserListRemote
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {
    @Singleton
    @get:Provides
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Logging)

        install(DefaultRequest) {
            headers.append(HttpHeaders.ContentType, "application/json; charset=utf-8")
            url {
                protocol = URLProtocol.HTTPS
                host = BuildConfig.GITHUB_API
            }
        }
    }

    @Provides
    fun provideUserDetailRemote(client: HttpClient): UserDetailRemote {
        return UserDetailRemote(client)
    }

    @Provides
    fun provideUserListRemote(client: HttpClient): UserListRemote {
        return UserListRemote(client)
    }
}
