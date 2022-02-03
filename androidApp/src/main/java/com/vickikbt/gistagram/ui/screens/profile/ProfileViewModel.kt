package com.vickikbt.gistagram.ui.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.vickikbt.gistagram.UserProfileQuery
import com.vickikbt.gistagram.repository.profile_repository.ProfileRepository
import com.vickikbt.gistagram.utils.UiState
import kotlinx.coroutines.launch
import timber.log.Timber

class ProfileViewModel constructor(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _userProfile = MutableLiveData<UiState<Response<UserProfileQuery.Data>>>()
    val userProfile: LiveData<UiState<Response<UserProfileQuery.Data>>> get() = _userProfile

    init {
        getLoggedInUserProfile()
    }
    fun getLoggedInUserProfile(login: String = "VictorKabata") = viewModelScope.launch {
        _userProfile.postValue(UiState.Loading())

        try {
            val response = profileRepository.getLoggedInUserProfile(login = login)
            _userProfile.postValue(UiState.Success(data = response))
        } catch (e: ApolloException) {
            _userProfile.postValue(UiState.Error(error = e.localizedMessage))
        }
    }

}