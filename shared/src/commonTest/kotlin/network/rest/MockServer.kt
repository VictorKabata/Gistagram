package network.rest

import com.goncalossilva.resources.Resource
import com.vickikbt.shared.domain.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlin.native.concurrent.ThreadLocal
import kotlinx.serialization.json.Json

@ThreadLocal
var networkSuccess: Boolean = true

object MockServer {

    private val mockMoviesResponse =
        Resource("src/commonTest/resources/user_token.json").readText()

    val mockHttpClient = HttpClient(MockEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

        engine {
            addHandler { request ->
                when (request.url.encodedPath) {
                    Constants.TOKEN_BASE_URL -> {
                        respond(
                            content = ByteReadChannel(mockMoviesResponse),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    else -> {
                        respond(
                            content = ByteReadChannel(mockMoviesResponse),
                            status = HttpStatusCode.InternalServerError,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                }
            }
        }
    }

    fun expectSuccess(isSuccess: Boolean = true) {
        networkSuccess = isSuccess
    }

}
