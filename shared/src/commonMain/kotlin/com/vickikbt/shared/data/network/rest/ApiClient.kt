package com.vickikbt.shared.data.network.rest

import com.vickikbt.shared.data.network.rest.models.AccessTokenDto
import com.vickikbt.shared.data.network.rest.models.UserDto
import com.vickikbt.shared.domain.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class ApiClient constructor(private val httpClient: HttpClient) {

    suspend fun fetchAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessTokenDto? {
        return try {
            httpClient.submitForm<AccessTokenDto>(
                url = Constants.TOKEN_BASE_URL,
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

    suspend fun fetchUserProfile(accessToken: String): UserDto? {
        return try {
            httpClient.get<UserDto>(
                urlString = "${Constants.REST_BASE_URL}/user"
            ) {
                headers {
                    append("Authorization", "Bearer $accessToken")
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
