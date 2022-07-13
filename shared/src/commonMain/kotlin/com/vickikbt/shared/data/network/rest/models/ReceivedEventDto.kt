package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ReceivedEventDto(
    @SerialName("id")
    val id: String?,

    @SerialName("type")
    val type: String,

    @SerialName("actor")
    val actor: ActorDto?,

    @SerialName("repo")
    val repo: RepoDto?,

    @SerialName("payload")
    val payload: Unit?,

    @SerialName("public")
    val public: Boolean?,

    @SerialName("created_at")
    val createdAt: String
) {
    /*var event: String? = payload
        set(value) {
            field = when {
                payload == "WatchEvent" -> Json.encodeToString(WatchEventDto::class)
                payload == "ForkEvent" -> Json.encodeToString(ForkeeDto::class)
                payload == "PublicEvent" -> value
                payload == "CreateEvent" -> Json.encodeToString(CreateEventDto::class)
                else -> null
            }
        }*/

    /*set() = when {
        payload == "WatchEvent" -> {
            Json.encodeToString(WatchEventDto::class)
        }
        payload == "" -> {
            Json.encodeToString(WatchEventDto::class)
        }
        payload == "" -> {
            Json.encodeToString(WatchEventDto::class)
        }
        else -> {
            Json.encodeToString(WatchEventDto::class)
        }
    }*/
}
