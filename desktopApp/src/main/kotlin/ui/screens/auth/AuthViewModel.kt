package ui.screens.auth

import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.shared.domain.utils.UiState
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import java.awt.Desktop
import java.net.URI
import java.net.URLEncoder
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume

class AuthViewModel constructor(private val authRepository: AuthRepository) : KoinComponent {

    private val _accessToken = MutableStateFlow<UiState<AccessToken?>>(UiState.Loading())
    val accessToken: StateFlow<UiState<AccessToken?>> get() = _accessToken

    private val viewModelScope = CoroutineScope(Dispatchers.IO)
    private val callbackJob = MutableStateFlow<Job?>(null)

    fun fetchOAuthCode() {
        val job = viewModelScope.launch {
            try {
                val redirectUri = "http://localhost:5789/callback"
                val encodedRedirectUri = URLEncoder.encode(redirectUri, Charsets.UTF_8)
                val webUrl =
                    "${Constants.OAUTH_BASE_URL}?client_id=${Constants.CLIENT_ID}&scope=repo,user,project,read:org&redirect_uri=$encodedRedirectUri"

                println("Launching URL: $webUrl")

                withContext(Dispatchers.IO) {
                    Desktop.getDesktop().browse(URI(webUrl))
                }

                val code = waitForCallback()

                fetchAccessToken(code = code)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        callbackJob.value = job
        job.invokeOnCompletion { callbackJob.value = null }
    }

    private suspend fun waitForCallback(): String {
        var server: NettyApplicationEngine? = null

        val code = suspendCancellableCoroutine<String> { continuation ->
            server = embeddedServer(Netty, port = 5789) {
                routing {
                    get("/callback") {
                        val code = call.parameters["code"]
                            ?: throw RuntimeException("Received a response with no code")
                        println("got code: $code")
                        call.respondText("OK")

                        continuation.resume(code)
                    }
                }
            }.start(wait = false)
        }

        viewModelScope.launch {
            server!!.stop(1, 5, TimeUnit.SECONDS)
        }

        return code
    }

    private fun fetchAccessToken(code: String) {
        _accessToken.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val response = authRepository.fetchAccessToken(code = code)
                println("Fetched access token: $response")
                println("Code: $code")
                _accessToken.value = UiState.Success(data = response)
            } catch (e: Exception) {
                _accessToken.value = UiState.Error(error = e.message)
            }
        }
    }

    fun cancelLogin() {
        callbackJob.value?.cancel()
        callbackJob.value = null
    }

}
