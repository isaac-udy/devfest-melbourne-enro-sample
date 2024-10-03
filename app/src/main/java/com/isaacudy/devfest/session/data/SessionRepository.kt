package com.isaacudy.devfest.session.data

import com.isaacudy.devfest.speaker.data.Speaker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update


/**
 * This is a fake repository that pretends to fetch data from a remote server, but really it's all just in-memory.
 */
class SessionRepository {
    private val sessionDataSource = MutableStateFlow(SessionData.allSessions)

    fun listSessions(): StateFlow<List<Session>> {
        return sessionDataSource
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSession(id: Session.Id): Flow<Session?> {
        return sessionDataSource
            .mapLatest { speakers -> speakers.firstOrNull { it.id == id } }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSessionsForSpeaker(speakerId: Speaker.Id): Flow<List<Session>> {
        return sessionDataSource
            .mapLatest { sessions -> sessions.filter { it.speaker == speakerId } }
    }

    fun saveSession(session: Session) {
        sessionDataSource.update { sessions ->
            sessions.filter { it.id != session.id } + session
        }
    }
}
