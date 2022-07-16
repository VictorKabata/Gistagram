package ui.screens.main

import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class MainViewModel constructor(
    private val authRepository: AuthRepository,
    private val settingsRepository: SettingsRepository
) : KoinComponent {

    private val _accessToken = MutableStateFlow<AccessToken?>(null)
    val accessToken = _accessToken.asStateFlow()

    private val _appTheme = MutableStateFlow<Int?>(null)
    val appTheme = _appTheme.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)
    private val supervisorJob = MutableStateFlow<Job?>(null)

    init {
        getAccessToken()
        getAppTheme()
    }

    private fun getAccessToken() {
        val job = viewModelScope.launch {
            val response = authRepository.getAccessToken()
            response.collect {
                _accessToken.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

    private fun getAppTheme() {
        val job = viewModelScope.launch {
            try {
                settingsRepository.getAppTheme().collect { theme ->
                    _appTheme.value = theme
                }
            } catch (e: Exception) {
                println("ERROR saving theme: ${e.localizedMessage}")
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

}
