package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.network.rest.models.PlanDto
import com.vickikbt.shared.data.network.rest.models.UserDto
import com.vickikbt.shared.domain.models.Plan
import com.vickikbt.shared.domain.models.User

internal fun UserDto.toDomain(): User {
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
        plan = this.planDto?.toDomain(),
        twitterUsername = this.twitterUsername,
        type = this.type,
        updatedAt = this.updatedAt,
        url = this.url
    )
}

internal fun PlanDto.toDomain(): Plan {
    return Plan(
        collaborators = this.collaborators,
        name = this.name,
        privateRepos = this.privateRepos,
        space = this.space
    )
}
