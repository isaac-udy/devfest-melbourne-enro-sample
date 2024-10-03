package com.isaacudy.devfest.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isaacudy.devfest.Dependencies
import com.isaacudy.devfest.session.data.Session
import com.isaacudy.devfest.shared.RequestStringDestination
import com.isaacudy.devfest.speaker.SelectSpeakerDestination
import com.isaacudy.devfest.speaker.data.Speaker
import com.isaacudy.devfest.ui.components.OutlinedValueField
import com.isaacudy.devfest.ui.components.ScreenTitle
import dev.enro.annotations.ExperimentalEnroApi
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.closeWithResult
import dev.enro.core.compose.navigationHandle
import dev.enro.core.compose.rememberNavigationContainer
import dev.enro.core.container.EmptyBehavior
import dev.enro.destination.flow.managedFlowDestination
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data object CreateSessionDestination : NavigationKey.SupportsPush.WithResult<Session.Id>

@OptIn(ExperimentalEnroApi::class)
@NavigationDestination(CreateSessionDestination::class)
val createSessionDestination = managedFlowDestination<CreateSessionDestination>()
    .flow {
        val title = push {
            RequestStringDestination(
                title = "Create Session",
                description = "Please enter the title of the session",
                label = "Title",
            )
        }
        val description = push {
            RequestStringDestination(
                title = "Create Session",
                description = "Please enter a description of the session",
                label = "Description",
                multiline = true,
            )
        }
        val speaker = push {
            SelectSpeakerForSessionCreationDestination
        }

        push {
            ConfirmSessionCreationDestination(
                title = title,
                description = description,
                speaker = speaker,
            )
        }

        Session(
            id = Session.Id.new(),
            title = title,
            description = description,
            speaker = speaker,
            dateTime = LocalDateTime.now(),
        )
    }
    .onComplete { session ->
        Dependencies.sessionRepository.saveSession(session)
        navigation.closeWithResult(session.id)
    }

@Parcelize
data object SelectSpeakerForSessionCreationDestination : NavigationKey.SupportsPush.WithResult<Speaker.Id>

@NavigationDestination(SelectSpeakerForSessionCreationDestination::class)
@Composable
fun SelectSpeakerForSessionCreationScreen() {
    val navigation = navigationHandle<SelectSpeakerForSessionCreationDestination>()
    val container = rememberNavigationContainer(
        root = SelectSpeakerDestination(null),
        emptyBehavior = EmptyBehavior.CloseParent,
        interceptor = {
            onResult<_, Speaker.Id> { _, result ->
                navigation.closeWithResult(result)
                cancelCloseAndResult()
            }
        }
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ScreenTitle(
            text = "Create Session",
            showBackground = false,
        )
        Text(
            text = "Please select a speaker for the session",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp),
        )
        container.Render()
    }
}

@Parcelize
data class ConfirmSessionCreationDestination(
    val title: String,
    val description: String,
    val speaker: Speaker.Id,
) : NavigationKey.SupportsPush.WithResult<Unit>


@NavigationDestination(ConfirmSessionCreationDestination::class)
@Composable
fun ConfirmInformationScreen() {
    val navigation = navigationHandle<ConfirmSessionCreationDestination>()
    val speaker = remember { Dependencies.speakerRepository.getSpeaker(navigation.key.speaker) }
        .collectAsState(null)
        .value

    Column {
        ScreenTitle(
            text = "Create Session",
            showBackground = false,
        )
        Text(
            text = "Please confirm the details of the session are correct before creating the session",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = navigation.key.title,
                label = "Title"
            )
            OutlinedValueField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = speaker?.name ?: "",
                trailingIcon = if (speaker == null) {
                    {
                        CircularProgressIndicator(
                            Modifier.size(24.dp),
                        )
                    }
                } else null,
                label = "Speaker",
            )
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = navigation.key.description,
                label = "Description"
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                navigation.closeWithResult(Unit)
            }
        ) {
            Text("Create")
        }
    }
}