package com.vickikbt.shared.data.cache.realm.dao

import com.vickikbt.shared.data.cache.realm.models.UserEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserDao constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val realmDatabase: Realm
) {

  //  val user = realmDatabase.query(UserEntity::class).first().find()?.asFlow()?.map { it.obj }

    val user = MutableStateFlow<UserEntity?>(null)





    init {


        CoroutineScope(ioDispatcher).launch {
            realmDatabase.query(UserEntity::class).first().asFlow().map {
                it.obj
            }.collect { userEntity ->

                user.value = userEntity
                println("starting realm $userEntity")
            }
        }
    }



    suspend fun saveUser(userEntity: UserEntity) {
        val user = UserEntity().apply {
            avatarUrl = userEntity.avatarUrl
            bio = userEntity.bio
            blog = userEntity.blog
            collaborators = userEntity.collaborators
            company = userEntity.company
            createdAt = userEntity.createdAt
            email = userEntity.email
            followers = userEntity.followers
            following = userEntity.following
            id = userEntity.id
            location = userEntity.location
            login = userEntity.login
            name = userEntity.name
            planEntity = userEntity.planEntity
            twitterUsername = userEntity.twitterUsername
            type = userEntity.type
            updatedAt = userEntity.updatedAt
            url = userEntity.url
        }

        CoroutineScope(ioDispatcher).async {
            realmDatabase.write {
                copyToRealm(user)
            }
        }
    }

    fun deleteUser() {
        realmDatabase.writeBlocking {
            val user = this.query(UserEntity::class).find()
            delete(user)
        }
    }
}
