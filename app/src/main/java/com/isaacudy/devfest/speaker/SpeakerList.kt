package com.isaacudy.devfest.speaker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isaacudy.devfest.Dependencies
import com.isaacudy.devfest.ui.components.ScreenTitle
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.compose.navigationHandle
import dev.enro.core.push
import kotlinx.parcelize.Parcelize

@Parcelize
data object SpeakerListDestination : NavigationKey.SupportsPush


@OptIn(ExperimentalFoundationApi::class)
@NavigationDestination(SpeakerListDestination::class)
@Composable
fun SpeakerListScreen() {
    val navigation = navigationHandle<SpeakerListDestination>()
    val speakers by remember { Dependencies.speakerRepository.listSpeakers() }.collectAsState()

    val listState = rememberLazyListState()
    val isAtTopOfList = remember (listState){ derivedStateOf { !listState.canScrollBackward } }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
        ) {
            stickyHeader {
                ScreenTitle(
                    text = "Speakers",
                    showBackground = !isAtTopOfList.value,
                )
            }
            items(
                items = speakers,
                key = { it.id },
                contentType = { "SpeakerItem" }
            ) { speaker ->
                ListItem(
                    modifier = Modifier.clickable {
                        navigation.push(SpeakerDetailDestination(speaker.id))
                    },
                    headlineContent = { Text(speaker.name) },
                    supportingContent = { Text(speaker.headline) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(96.dp))
            }
        }
        ExtendedFloatingActionButton(
            onClick = {
                navigation.push(CreateSpeakerDestination)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            expanded = isAtTopOfList.value,
            text = { Text("Add Speaker") },
            icon = { Icon(Icons.Default.PersonAddAlt1, null) }
        )
    }
}