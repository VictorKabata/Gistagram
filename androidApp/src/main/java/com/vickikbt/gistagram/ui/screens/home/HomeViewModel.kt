package com.vickikbt.gistagram.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.User
import com.vickikbt.shared.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    fun getUser() {
        viewModelScope.launch {
            try {
                val response = authRepository.getUser()
                response?.collect {
                    _user.value = it
                }
            } catch (e: Exception) {
                Napier.e("ERROR saving theme: ${e.localizedMessage}")
            }
        }
    }
}
