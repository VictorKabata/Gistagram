package com.vickikbt.gistagram.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.data.network.rest.models.ReceivedEventDto
import com.vickikbt.shared.domain.repositories.FeedsRepository
import com.vickikbt.shared.presentation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val feedsRepository: FeedsRepository) :
    ViewModel() {

    private val _receivedFeeds = MutableStateFlow<UiState<List<ReceivedEventDto?>?>?>(null)
    val receivedFeeds = _receivedFeeds.asStateFlow()

    init {
        //getReceivedFeeds()
    }

    private fun getReceivedFeeds() {
        _receivedFeeds.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val response = feedsRepository.fetchReceivedEvents()

                response?.collectLatest {
                    _receivedFeeds.value = UiState.Success(data = it)
                }
            } catch (e: Exception) {
                _receivedFeeds.value = UiState.Error(error = e.localizedMessage)
            }
        }
    }

}
