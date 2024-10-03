@file:OptIn(ExperimentalCoroutinesApi::class)

package com.isaacudy.devfest.session

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isaacudy.devfest.Dependencies
import com.isaacudy.devfest.session.data.Session
import com.isaacudy.devfest.speaker.SelectSpeakerDestination
import com.isaacudy.devfest.speaker.SpeakerDetailDestination
import com.isaacudy.devfest.speaker.data.Speaker
import com.isaacudy.devfest.ui.components.OutlinedValueField
import com.isaacudy.devfest.ui.components.ScreenTitle
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.push
import dev.enro.core.result.registerForNavigationResult
import dev.enro.viewmodel.navigationHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.parcelize.Parcelize
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Parcelize
data class SessionDetailDestination(
    val id: Session.Id,
) : NavigationKey.SupportsPush

class SessionDetailViewModel : ViewModel() {
    private val navigation by navigationHandle<SessionDetailDestination>()
    private val editSpeaker by registerForNavigationResult<Speaker.Id> { selected ->
        onSpeakerEdited(selected)
    }

    val session = Dependencies.sessionRepository
        .getSession(navigation.key.id)
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val speaker = session.mapNotNull { it?.speaker }
        .flatMapLatest { Dependencies.speakerRepository.getSpeaker(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun onSpeakerSelected() {
        val session = session.value ?: return
        navigation.push(
            SpeakerDetailDestination(
                id = session.speaker
            )
        )
    }

    fun onEditSpeakerSelected() {
        val session = session.value ?: return
        editSpeaker.present(
            SelectSpeakerDestination(
                selected = session.speaker
            )
        )
    }

    private fun onSpeakerEdited(speakerId: Speaker.Id) {
        val session = session.value ?: return
        Dependencies.sessionRepository.saveSession(session.copy(speaker = speakerId))
    }
}

@NavigationDestination(SessionDetailDestination::class)
@Composable
fun SessionDetailScreen(
    viewModel: SessionDetailViewModel = viewModel()
) {
    val session = viewModel.session.collectAsState().value
    val speaker = viewModel.speaker.collectAsState().value

    if (session == null) {
        Text("Error loading session")
        return
    }

    val scrollState = rememberScrollState()
    Column {
        ScreenTitle(
            text = "Session",
            showBackground = scrollState.canScrollBackward,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = session.title,
                label = "Title"
            )
            OutlinedValueField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onSpeakerSelected() },
                value = speaker?.name ?: "",
                label = "Speaker",
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.onEditSpeakerSelected() }
                    ) {
                        Icon(Icons.Default.Edit, null)
                    }
                }
            )
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = session.dateTime.format(
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                ),
                label = "Scheduled Time"
            )
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = session.description,
                label = "Description"
            )
        }
    }
}