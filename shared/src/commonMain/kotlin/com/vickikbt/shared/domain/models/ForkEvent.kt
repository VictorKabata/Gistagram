package com.vickikbt.shared.domain.models

data class ForkEvent(
    val forkee: Forkee?
)

data class Forkee(
    val id:Int?,
    val name:String?,
    val fullName:String?,
    val description:String?,
    val fork:Boolean?,
    val stargazersCount:Int?,
    val watcherCount:Int?,
    val forks:Int?,
    val createdAt:String?,
    val updatedAt:String?
)
