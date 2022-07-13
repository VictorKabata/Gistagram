package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(

    @SerialName("avatar_url")
    val avatarUrl: String?,

    @SerialName("bio")
    val bio: String?,

    @SerialName("blog")
    val blog: String?,

    @SerialName("collaborators")
    val collaborators: Int?,

    @SerialName("company")
    val company: String?,

    @SerialName("created_at")
    val createdAt: String?,

    @SerialName("email")
    val email: String?,

    @SerialName("followers")
    val followers: Int?,

    @SerialName("followers_url")
    val followersUrl: String?,

    @SerialName("following")
    val following: Int?,

    @SerialName("id")
    val id: Int?,

    @SerialName("location")
    val location: String?,

    @SerialName("login")
    val login: String?,

    @SerialName("name")
    val name: String?,

    @SerialName("plan")
    val planDto: PlanDto?,

    @SerialName("twitter_username")
    val twitterUsername: String?,

    @SerialName("type")
    val type: String?,

    @SerialName("updated_at")
    val updatedAt: String?,

    @SerialName("url")
    val url: String?
)
