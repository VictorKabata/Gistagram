package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.network.rest.models.PlanDto
import com.vickikbt.shared.data.network.rest.models.UserDto
import com.vickikbt.shared.domain.models.Plan
import com.vickikbt.shared.domain.models.User

internal fun UserDto.toDomain(): User {
    return User(
        avatar_url = this.avatar_url,
        bio = this.bio,
        blog = this.blog,
        collaborators = this.collaborators,
        company = this.company,
        created_at = this.created_at,
        email = this.email,
        followers = this.followers,
        following = this.following,
        id = this.id,
        location = this.location,
        login = this.login,
        name = this.name,
        plan = this.planDto?.toDomain(),
        twitter_username = this.twitter_username,
        type = this.type,
        updated_at = this.updated_at,
        url = this.url
    )
}

internal fun PlanDto.toDomain(): Plan {
    return Plan(
        collaborators = this.collaborators,
        name = this.name,
        private_repos = this.private_repos,
        space = this.space
    )
}
