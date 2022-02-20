package com.vickikbt.shared.data.network.rest

import com.vickikbt.shared.data.network.rest.models.TokenDto
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class ApiClientImpl constructor(private val httpClient: HttpClient) : ApiClient {

    override suspend fun getUserToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): TokenDto? {
        return try {
            httpClient.submitForm<TokenDto>(
                url = "https://github.com/login/oauth/access_token",
                formParameters = Parameters.build {
                    append("client_id", clientId)
                    append("client_secret", clientSecret)
                    append("code", code)
                },
                encodeInQuery = true
            ) {
                headers {
                    append(HttpHeaders.Accept, "application/json")
                }
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            null
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            null
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}
