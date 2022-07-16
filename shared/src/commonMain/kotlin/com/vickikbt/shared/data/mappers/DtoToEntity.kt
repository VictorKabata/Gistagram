package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.realm.models.PlanEntity
import com.vickikbt.shared.data.cache.realm.models.UserEntity
import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.data.network.rest.models.AccessTokenDto
import com.vickikbt.shared.data.network.rest.models.PlanDto
import com.vickikbt.shared.data.network.rest.models.UserDto

internal fun AccessTokenDto.toEntity(): AccessTokenEntity {
    return AccessTokenEntity(
        accessToken = this.accessToken,
        scope = this.scope,
        tokenType = this.tokenType,
    )
}

internal fun UserDto.toEntity(): UserEntity {
    return UserEntity().apply {
        avatarUrl = this@toEntity.avatarUrl
        bio = this@toEntity.bio
        blog = this@toEntity.blog
        collaborators = this@toEntity.collaborators
        company = this@toEntity.company
        createdAt = this@toEntity.createdAt
        email = this@toEntity.email
        followers = this@toEntity.followers
        following = this@toEntity.following
        id = this@toEntity.id
        location = this@toEntity.location
        login = this@toEntity.login
        name = this@toEntity.name
        planEntity = this@toEntity.planDto?.toEntity()
        twitterUsername = this@toEntity.twitterUsername
        type = this@toEntity.type
        updatedAt = this@toEntity.updatedAt
        url = this@toEntity.url
    }
}

internal fun PlanDto.toEntity(): PlanEntity {
    return PlanEntity().apply {
        collaborators = this@toEntity.collaborators
        name = this@toEntity.name
        privateRepos = this@toEntity.privateRepos
        space = this.space
    }
}
