package com.vickikbt.shared.data.cache.realm.models

import io.realm.kotlin.types.RealmObject

open class PlanEntity : RealmObject {
    var collaborators: Int? = null
    var name: String? = null
    var privateRepos: Int? = null
    var space: Int? = null
}
