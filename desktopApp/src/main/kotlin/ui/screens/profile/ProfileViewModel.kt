package ui.screens.profile

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.shared.domain.repositories.ProfileRepository
import com.vickikbt.shared.domain.utils.UiState
import koin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel constructor(private val profileRepository: ProfileRepository = koin.get()) {

    private val _userProfile =
        MutableStateFlow<UiState<ApolloResponse<LoggedInUserProfileQuery.Data>>?>(null)
    val userProfile = _userProfile.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)
    private val callbackJob = MutableStateFlow<Job?>(null)

    init {
        getLoggedInUserProfile()
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

        callbackJob.value = job
        job.invokeOnCompletion {
            callbackJob.value?.cancel()
            callbackJob.value = null
        }
    }

}
