package com.isaacudy.devfest.speaker

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.isaacudy.devfest.Dependencies
import com.isaacudy.devfest.speaker.data.Speaker
import dev.enro.annotations.AdvancedEnroApi
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationDirection
import dev.enro.core.NavigationKey
import dev.enro.core.TypedNavigationHandle
import dev.enro.core.closeWithResult
import dev.enro.core.compose.OverrideNavigationAnimations
import dev.enro.core.compose.navigationHandle
import dev.enro.core.requestClose
import dev.enro.core.result.deliverResultFromPush
import kotlinx.parcelize.Parcelize

@Parcelize
class SelectSpeakerDestination(
    val selected: Speaker.Id? = null,
) : NavigationKey.SupportsPush.WithResult<Speaker.Id>,
    NavigationKey.SupportsPresent.WithResult<Speaker.Id>

@OptIn(ExperimentalMaterial3Api::class, AdvancedEnroApi::class)
@NavigationDestination(SelectSpeakerDestination::class)
@Composable
fun SelectSpeakerScreen() {
    val navigation = navigationHandle<SelectSpeakerDestination>()
    val speakers = remember { Dependencies.speakerRepository.listSpeakers() }
        .collectAsState()
        .value
    val isPresented = navigation.instruction.navigationDirection == NavigationDirection.Present

    if (isPresented) {
        OverrideNavigationAnimations(
            enter = EnterTransition.None,
            exit = ExitTransition.None
        )
        ModalBottomSheet(
            onDismissRequest = { navigation.requestClose() },
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            SelectSpeakerContent(
                navigation = navigation,
                speakers = speakers,
                includeSystemPadding = false
            )
        }
    } else {
        SelectSpeakerContent(
            navigation = navigation,
            speakers = speakers,
            includeSystemPadding = true
        )
    }
}

@Composable
private fun SelectSpeakerContent(
    navigation: TypedNavigationHandle<SelectSpeakerDestination>,
    speakers: List<Speaker>,
    includeSystemPadding: Boolean
) {
    LazyColumn(
        modifier = if (includeSystemPadding) {
            Modifier
                .systemBarsPadding()
                .navigationBarsPadding()
        } else {
            Modifier
        }
    ) {
        item {
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigation.deliverResultFromPush(CreateSpeakerDestination) },
                headlineContent = { Text(text = "New Speaker") },
                leadingContent = {
                    Icon(Icons.Default.Add, null)
                }
            )
        }
        items(speakers) { speaker ->
            val isSelected = speaker.id == navigation.key.selected
            Column {
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigation.closeWithResult(speaker.id) },
                    headlineContent = { Text(text = speaker.name) },
                    trailingContent = if (isSelected) {
                        {
                            Icon(Icons.Default.Check, null)
                        }
                    } else null,
                )
            }
        }
    }
}