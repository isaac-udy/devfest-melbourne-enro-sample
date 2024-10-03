package com.isaacudy.devfest.speaker.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

/**
 * This is a fake repository that pretends to fetch data from a remote server, but really it's all just in-memory.
 */
class SpeakerRepository {
    private val speakerDataSource = MutableStateFlow(SpeakerData.allSpeakers)

    fun listSpeakers(): StateFlow<List<Speaker>> {
        return speakerDataSource
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSpeaker(id: Speaker.Id): Flow<Speaker?> {
        return speakerDataSource
            .mapLatest { speakers -> speakers.firstOrNull { it.id == id } }
    }

    fun saveSpeaker(speaker: Speaker) {
        speakerDataSource.update { speakers ->
            speakers.filter { it.id != speaker.id } + speaker
        }
    }
}
