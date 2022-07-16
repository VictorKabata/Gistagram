package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.realm.models.PlanEntity
import com.vickikbt.shared.data.cache.realm.models.UserEntity
import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.models.Plan
import com.vickikbt.shared.domain.models.User

internal fun AccessTokenEntity.toDomain(): AccessToken {
    return AccessToken(
        accessToken = this.accessToken,
        scope = this.scope,
        tokenType = this.tokenType
    )
}

internal fun UserEntity.toDomain(): User {
    return User(
        avatarUrl = this.avatarUrl,
        bio = this.bio,
        blog = this.blog,
        collaborators = this.collaborators,
        company = this.company,
        createdAt = this.createdAt,
        email = this.email,
        followers = this.followers,
        following = this.following,
        id = this.id,
        location = this.location,
        login = this.login,
        name = this.name,
        plan = this.planEntity?.toDomain(),
        twitterUsername = this.twitterUsername,
        type = this.type,
        updatedAt = this.updatedAt,
        url = this.url
    )
}

internal fun PlanEntity.toDomain(): Plan {
    return Plan(
        collaborators = this.collaborators,
        name = this.name,
        privateRepos = this.privateRepos,
        space = this.space
    )
}
