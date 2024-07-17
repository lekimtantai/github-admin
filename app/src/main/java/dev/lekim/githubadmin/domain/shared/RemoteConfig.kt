package dev.lekim.githubadmin.domain.shared

import dev.lekim.githubadmin.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol

class RemoteConfig {
    companion object {
        val client = HttpClient(CIO) {
            install(Logging)

            install(DefaultRequest) {
                headers.append(HttpHeaders.ContentType, "application/json; charset=utf-8")
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.GITHUB_API
                }
            }
        }
    }
}