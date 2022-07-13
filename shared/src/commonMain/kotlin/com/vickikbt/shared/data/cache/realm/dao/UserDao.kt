package com.vickikbt.shared.data.cache.realm.dao

import com.vickikbt.shared.data.cache.realm.models.UserEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class UserDao constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val realmDatabase: Realm
) {

    val user = realmDatabase.query(UserEntity::class).first().find()

    suspend fun saveUser(userEntity: UserEntity) {
        CoroutineScope(ioDispatcher).async {
            realmDatabase.write {
                copyToRealm(UserEntity())
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
