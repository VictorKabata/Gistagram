package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.data.network.rest.models.ReceivedEventDto
import kotlinx.coroutines.flow.Flow

interface FeedsRepository {

    suspend fun fetchReceivedEvents(): Flow<List<ReceivedEventDto?>?>? //ToDo: Return domain model instead

}
