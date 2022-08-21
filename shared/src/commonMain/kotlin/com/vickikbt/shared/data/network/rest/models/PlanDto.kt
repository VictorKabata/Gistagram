package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanDto(
    @SerialName("collaborators")
    val collaborators: Int?,

    @SerialName("name")
    val name: String?,

    @SerialName("private_repos")
    val privateRepos: Int?,

    @SerialName("space")
    val space: Int?
)
