package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDto(

    @SerialName("avatar_url")
    val avatar_url: String?,

    @SerialName("bio")
    val bio: String?,

    @SerialName("blog")
    val blog: String?,

    @SerialName("collaborators")
    val collaborators: Int?,

    @SerialName("company")
    val company: String?,

    @SerialName("created_at")
    val created_at: String?,

    @SerialName("disk_usage")
    val disk_usage: Int?,

    @SerialName("email")
    val email: String?,

    @SerialName("events_url")
    val events_url: String?,

    @SerialName("followers")
    val followers: Int?,

    @SerialName("followers_url")
    val followers_url: String?,

    @SerialName("following")
    val following: Int?,

    @SerialName("following_url")
    val following_url: String?,

    @SerialName("gists_url")
    val gists_url: String?,

    @SerialName("gravatar_id")
    val gravatar_id: String?,

    @SerialName("hireable")
    val hireable: Boolean?,

    @SerialName("html_url")
    val html_url: String?,

    @SerialName("id")
    val id: Int?,

    @SerialName("location")
    val location: String?,

    @SerialName("login")
    val login: String?,

    @SerialName("name")
    val name: String?,

    @SerialName("node_id")
    val node_id: String?,

    @SerialName("organizations_url")
    val organizations_url: String?,

    @SerialName("owned_private_repos")
    val owned_private_repos: Int?,

    @SerialName("planDto")
    val planDto: PlanDto?,

    @SerialName("private_gists")
    val private_gists: Int?,

    @SerialName("public_gists")
    val public_gists: Int?,

    @SerialName("public_repos")
    val public_repos: Int?,

    @SerialName("received_events_url")
    val received_events_url: String?,

    @SerialName("repos_url")
    val repos_url: String?,

    @SerialName("site_admin")
    val site_admin: Boolean?,

    @SerialName("starred_url")
    val starred_url: String?,

    @SerialName("subscriptions_url")
    val subscriptions_url: String?,

    @SerialName("total_private_repos")
    val total_private_repos: Int?,

    @SerialName("twitter_username")
    val twitter_username: String?,

    @SerialName("two_factor_authentication")
    val two_factor_authentication: Boolean?,

    @SerialName("type")
    val type: String?,

    @SerialName("updated_at")
    val updated_at: String?,

    @SerialName("url")
    val url: String?
)
