package com.vickikbt.gistagram.utils

import coil.network.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Result<T>) = flow {

    try {
        //ToDo: Check if internet connected-Return internet error
        emit(Result.Success(data = apiCall.invoke()))
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> emit(Result.Error(error = throwable.localizedMessage ?: "IO Exception"))
            is HttpException -> emit(Result.Error(error = throwable.localizedMessage ?: "Http Exception"))
            else -> emit(Result.Error(error = "Unknown error"))
        }
    }
}.flowOn(Dispatchers.IO)