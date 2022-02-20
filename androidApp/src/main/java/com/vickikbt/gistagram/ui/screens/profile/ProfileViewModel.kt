package com.vickikbt.gistagram.ui.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.gistagram.utils.UiState
import com.vickikbt.shared.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class ProfileViewModel constructor(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _userProfile = MutableLiveData<UiState<ApolloResponse<LoggedInUserProfileQuery.Data>>>()
    val userProfile: LiveData<UiState<ApolloResponse<LoggedInUserProfileQuery.Data>>> get() = _userProfile

    init {
        getLoggedInUserProfile()
    }

    fun getLoggedInUserProfile() = viewModelScope.launch {
        _userProfile.postValue(UiState.Loading())

        try {
            val response = profileRepository.getLoggedInUserProfile()
            response.collectLatest {
                _userProfile.postValue(UiState.Success(data = it))
            }
        } catch (e: ApolloHttpException) {
            Timber.e("Failure ${e.localizedMessage}")
            _userProfile.postValue(UiState.Error(error = e.localizedMessage))
        } catch (e: Exception) {
            Timber.e("Failure ${e.message}")
            _userProfile.postValue(UiState.Error(error = e.localizedMessage))
        }
    }
}
