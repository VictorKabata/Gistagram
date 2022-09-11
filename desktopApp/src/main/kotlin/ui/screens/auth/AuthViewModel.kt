package ui.screens.auth

import com.vickikbt.shared.domain.models.User
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.utils.Configs
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.shared.presentation.UiState
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.engine.stop
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import java.awt.Desktop
import java.net.URI
import java.net.URLEncoder
import java.util.concurrent.TimeUnit
import koin
import kotlin.coroutines.resume
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

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
                            ?: throw IllegalArgumentException("Received a response with no code")

                        call.respondText("OK")

                        continuation.resume(code)
                    }
                }
            }.start(wait = false)
        }

        viewModelScope.launch {
            server?.let {
                it.stop(1, 5, TimeUnit.SECONDS)
            }
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
