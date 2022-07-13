package com.vickikbt.shared.domain.models

data class User(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val collaborators:Int?,
    val company: String?,
    val created_at: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val plan: Plan?,
    val twitter_username: String?,
    val type: String?,
    val updated_at: String?,
    val url: String?
)
