package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventEntity(
    @SerialName("ref")
    val ref: String?,

    @SerialName("ref_type")
    val refType: String?,

    @SerialName("master_branch")
    val masterBranch: String?,

    @SerialName("description")
    val description: String?,

    @SerialName("pusher_type")
    val pusherType: String?
)
