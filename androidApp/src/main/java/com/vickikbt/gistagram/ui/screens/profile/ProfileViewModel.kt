package com.vickikbt.gistagram.ui.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.shared.domain.repositories.ProfileRepository
import com.vickikbt.shared.domain.utils.UiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel constructor(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _userProfile =
        MutableLiveData<UiState<ApolloResponse<LoggedInUserProfileQuery.Data>>>()
    val userProfile: LiveData<UiState<ApolloResponse<LoggedInUserProfileQuery.Data>>> get() = _userProfile

    init {
        getLoggedInUserProfile()
    }

    private fun getLoggedInUserProfile() = viewModelScope.launch {
        _userProfile.value = UiState.Loading()

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
}
