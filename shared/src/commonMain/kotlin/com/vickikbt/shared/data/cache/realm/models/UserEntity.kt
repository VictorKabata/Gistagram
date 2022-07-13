package com.vickikbt.shared.data.cache.realm.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class UserEntity : RealmObject {
    var avatarUrl: String? = null
    var bio: String? = null
    var blog: String? = null
    var collaborators: Int? = null
    var company: String? = null
    var createdAt: String? = null
    var email: String? = null
    var followers: Int? = null
    var following: Int? = null
    @PrimaryKey
    var id: Int? = 0
    var location: String? = null
    var login: String? = null
    var name: String? = null
    var planEntity: PlanEntity? = null
    var twitterUsername: String? = null
    var type: String? = null
    var updatedAt: String? = null
    var url: String? = null
}
