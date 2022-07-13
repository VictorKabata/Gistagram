package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchEventEntity(
    @SerialName("action")
    val action: String? = null
)
