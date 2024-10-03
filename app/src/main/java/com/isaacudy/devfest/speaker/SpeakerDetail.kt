package com.isaacudy.devfest.speaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isaacudy.devfest.Dependencies
import com.isaacudy.devfest.speaker.data.Speaker
import com.isaacudy.devfest.ui.components.OutlinedValueField
import com.isaacudy.devfest.ui.components.ScreenTitle
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.compose.navigationHandle
import kotlinx.parcelize.Parcelize

@Parcelize
class SpeakerDetailDestination(
    val id: Speaker.Id
) : NavigationKey.SupportsPush

@NavigationDestination(SpeakerDetailDestination::class)
@Composable
fun SpeakerDetailScreen() {
    val navigation = navigationHandle<SpeakerDetailDestination>()

    val speaker = remember {
        Dependencies.speakerRepository.getSpeaker(navigation.key.id)
    }.collectAsState(initial = null).value

    if (speaker == null) {
        Text("Error loading speaker")
        return
    }

    val scrollState = rememberScrollState()
    Column {
        ScreenTitle(
            text = "Speaker",
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
                value = speaker.name,
                label = "Name"
            )
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = speaker.headline,
                label = "Headline"
            )
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = speaker.biography,
                label = "Biography"
            )
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = speaker.email,
                label = "Email Address"
            )
            OutlinedValueField(
                modifier = Modifier.fillMaxWidth(),
                value = speaker.phone,
                label = "Phone Number"
            )
        }
    }
}