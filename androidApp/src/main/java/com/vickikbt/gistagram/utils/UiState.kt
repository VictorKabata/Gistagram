package com.vickikbt.gistagram.utils

sealed class UiState<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Loading<T> : UiState<T>()
    class Success<T>(data: T) : UiState<T>(data)
    class Error<T>(error: String?, data: T? = null) : UiState<T>(data, error)
}
