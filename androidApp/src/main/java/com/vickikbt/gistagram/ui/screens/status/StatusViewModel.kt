package com.vickikbt.gistagram.ui.screens.status

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.vickikbt.gistagram.UserStatusQuery
import com.vickikbt.shared.domain.repositories.ProfileRepository
import com.vickikbt.shared.domain.utils.UiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StatusViewModel constructor(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _userStatus = MutableLiveData<UiState<ApolloResponse<UserStatusQuery.Data>>>()
    val userStatus: LiveData<UiState<ApolloResponse<UserStatusQuery.Data>>> get() = _userStatus

    init {
        getUserStatus()
    }

    private fun getUserStatus(userLogin: String = "VictorKabata") = viewModelScope.launch {
        _userStatus.postValue(UiState.Loading())

        try {
            val response = profileRepository.getUserStatus(userLogin = userLogin)
            response.collectLatest {
                _userStatus.postValue(UiState.Success(data = it))
            }
        } catch (e: ApolloHttpException) {
            _userStatus.postValue(UiState.Error(error = e.localizedMessage))
        } catch (e: Exception) {
            _userStatus.postValue(UiState.Error(error = e.localizedMessage))
        }
    }
}
