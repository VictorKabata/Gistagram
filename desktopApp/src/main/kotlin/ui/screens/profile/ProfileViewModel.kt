package ui.screens.profile

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.shared.domain.repositories.ProfileRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.presentation.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ProfileViewModel constructor(
    private val profileRepository: ProfileRepository,
    private val settingsRepository: SettingsRepository
) : KoinComponent {

    private val _userProfile =
        MutableStateFlow<UiState<ApolloResponse<LoggedInUserProfileQuery.Data>>?>(null)
    val userProfile = _userProfile.asStateFlow()

    private val _appTheme = MutableStateFlow<Int?>(null)
    val appTheme = _appTheme.asStateFlow()

    private val supervisorJob = MutableStateFlow<Job?>(null)
    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    init {
        getLoggedInUserProfile()
        getAppTheme()
    }

    private fun getLoggedInUserProfile() {
        _userProfile.value = UiState.Loading()
        val job = viewModelScope.launch {
            try {
                val response = profileRepository.fetchLoggedInUserProfile()
                response.collectLatest {
                    _userProfile.value = UiState.Success(data = it)
                }
            } catch (e: ApolloHttpException) {
                _userProfile.value = UiState.Error(error = e.localizedMessage)
            } catch (e: Exception) {
                _userProfile.value = UiState.Error(error = e.localizedMessage)
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

    fun setAppTheme(theme: Int) {
        println("Setting theme as: $theme")
        val job = viewModelScope.launch {
            try {
                settingsRepository.saveAppTheme(theme = theme)
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
