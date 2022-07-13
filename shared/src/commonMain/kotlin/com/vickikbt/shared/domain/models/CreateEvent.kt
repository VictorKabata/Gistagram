package com.vickikbt.shared.domain.models


data class CreateEvent(
    val ref: String?,
    val refType: String?,
    val masterBranch: String?,
    val description: String?,
    val pusherType: String?
)
