package com.vickikbt.shared.domain.repositories

interface FeedsRepository {

    suspend fun fetchReceivedEvents():Any?

}
