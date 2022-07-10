package ui.screens.main

import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class MainViewModel constructor(private val authRepository: AuthRepository) : KoinComponent {

    private val _accessToken = MutableStateFlow<AccessToken?>(null)
    val accessToken: StateFlow<AccessToken?> get() = _accessToken

    private val viewModelScope = CoroutineScope(Dispatchers.IO)
    private val callbackJob = MutableStateFlow<Job?>(null)

    init {
        getAccessToken()
    }

    private fun getAccessToken() {
        val job = viewModelScope.launch {
            val response = authRepository.getAccessToken()
            response.collect {
                _accessToken.value = it
            }
        }

        callbackJob.value = job
        job.invokeOnCompletion {
            callbackJob.value?.cancel() //ToDo: Might cause bug
            callbackJob.value = null
        }
    }

}
