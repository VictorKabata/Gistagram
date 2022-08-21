package com.vickikbt.shared.domain.models

data class User(
    val avatarUrl: String?,
    val bio: String?,
    val blog: String?,
    val collaborators: Int?,
    val company: String?,
    val createdAt: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val plan: Plan?,
    val twitterUsername: String?,
    val type: String?,
    val updatedAt: String?,
    val url: String?
)
