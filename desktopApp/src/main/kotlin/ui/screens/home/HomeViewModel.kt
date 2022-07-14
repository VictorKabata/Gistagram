package ui.screens.home

import com.vickikbt.shared.domain.models.User
import com.vickikbt.shared.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel constructor(private val authRepository: AuthRepository) : KoinComponent {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    private val supervisorJob = MutableStateFlow<Job?>(null)
    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    init {
        getUser()
    }

    private fun getUser() {
        val job = viewModelScope.launch {
            try {
                val response = authRepository.getUser()
                response?.collect {
                    _user.value = it
                }
            } catch (e: Exception) {
                Napier.e("ERROR saving theme: ${e.localizedMessage}")
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

}
