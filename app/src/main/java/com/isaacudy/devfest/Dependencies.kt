package com.isaacudy.devfest

import com.isaacudy.devfest.session.data.SessionRepository
import com.isaacudy.devfest.speaker.data.SpeakerRepository

/**
 * Simple class to hold all dependencies in one place, in place of using proper dependency injection.
 */
object Dependencies {
    val speakerRepository = SpeakerRepository()
    val sessionRepository = SessionRepository()
}