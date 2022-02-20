package com.vickikbt.gistagram.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.vickikbt.gistagram.repository.profile_repository.ProfileRepository

class ProfileViewModel constructor(private val profileRepository: ProfileRepository) : ViewModel() {

    /*private val _userProfile = MutableLiveData<UiState<Response<UserProfileQuery.Data>>>()
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
    }*/
}
