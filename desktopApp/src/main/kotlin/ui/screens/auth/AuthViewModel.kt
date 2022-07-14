package ui.screens.auth

import com.vickikbt.shared.domain.models.User
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.utils.Configs
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.shared.presentation.UiState
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import koin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import java.awt.Desktop
import java.net.URI
import java.net.URLEncoder
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume

class AuthViewModel constructor(private val authRepository: AuthRepository = koin.get()) :
    KoinComponent {

    private val _userUiState = MutableStateFlow<UiState<User?>?>(null)
    val userUiState = _userUiState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)
    private val callbackJob = MutableStateFlow<Job?>(null)

    fun fetchOAuthCode() {
        val job = viewModelScope.launch {
            try {
                val encodedRedirectUri = URLEncoder.encode(Configs.REDIRECT_URI, Charsets.UTF_8)
                val webUrl =
                    "${Constants.OAUTH_BASE_URL}?client_id=${Configs.CLIENT_ID}&scope=repo,user,project,read:org&redirect_uri=$encodedRedirectUri"

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
        job.invokeOnCompletion {
            callbackJob.value?.cancel()
            callbackJob.value = null
        }
    }

    private suspend fun waitForCallback(): String {
        var server: NettyApplicationEngine? = null

        val code = suspendCancellableCoroutine<String> { continuation ->
            server = embeddedServer(Netty, port = 5789) {
                routing {
                    get("/callback") {
                        val code = call.parameters["code"]
                            ?: throw RuntimeException("Received a response with no code")
                        println("Code: $code")
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

    private suspend fun fetchAccessToken(code: String) {
        _userUiState.value = UiState.Loading()

        try {
            val response = authRepository.fetchAccessToken(code = code)

            fetchUserProfile()

        } catch (e: Exception) {
            _userUiState.value = UiState.Error(error = e.message)
        }
    }

    private fun fetchUserProfile() {
        _userUiState.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val response = authRepository.fetchUserProfile()
                println("User fetched: $response")
                _userUiState.value = UiState.Success(data = response)
            } catch (e: Exception) {
                _userUiState.value = UiState.Error(error = e.localizedMessage)
            }
        }
    }

    fun cancelLogin() {
        callbackJob.value?.cancel()
        callbackJob.value = null
    }

}
