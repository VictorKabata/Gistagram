package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForkEvent(
    @SerialName("forkee")
    val forkee: ForkeeEntity?
)

@Serializable
data class ForkeeEntity(
    @SerialName("id")
    val id: Int?,

    @SerialName("name")
    val name: String?,

    @SerialName("full_name")
    val fullName: String?,

    @SerialName("description")
    val description: String?,

    @SerialName("fork")
    val fork: Boolean?,

    @SerialName("stargazers_count")
    val stargazersCount: Int?,

    @SerialName("watcher_count")
    val watcherCount: Int?,

    @SerialName("forks")
    val forks: Int?,

    @SerialName("created_at")
    val createdAt: String?,

    @SerialName("updated_at")
    val updatedAt: String?
)
