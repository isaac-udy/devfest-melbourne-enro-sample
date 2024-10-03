package com.isaacudy.devfest.speaker.data

import android.os.Parcelable
import com.isaacudy.devfest.session.data.Session.Id
import kotlinx.parcelize.Parcelize
import java.util.UUID

data class Speaker(
    val id: Id,
    val name: String,
    val headline: String,
    val biography: String,
    val email: String,
    val phone: String,
) {
    @JvmInline
    @Parcelize
    value class Id private constructor(val value: String) : Parcelable {
        companion object {
            fun new() = Id(UUID.randomUUID().toString())
        }
    }
}
