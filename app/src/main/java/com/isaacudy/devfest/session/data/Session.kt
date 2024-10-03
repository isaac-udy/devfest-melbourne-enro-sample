package com.isaacudy.devfest.session.data

import android.os.Parcelable
import com.isaacudy.devfest.speaker.data.Speaker
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.UUID

data class Session(
    val id: Id,
    val title: String,
    val description: String,
    val dateTime: LocalDateTime,
    val speaker: Speaker.Id,
) {
    @JvmInline
    @Parcelize
    value class Id private constructor(val value: String) : Parcelable {
        companion object {
            fun new() = Id(UUID.randomUUID().toString())
        }
    }
}