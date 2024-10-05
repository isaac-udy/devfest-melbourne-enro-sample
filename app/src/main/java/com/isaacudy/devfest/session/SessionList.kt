package com.isaacudy.devfest.session

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
import androidx.compose.material.icons.filled.BookmarkAdd
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isaacudy.devfest.Dependencies
import com.isaacudy.devfest.session.data.Session
import com.isaacudy.devfest.ui.components.ScreenTitle
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.push
import dev.enro.viewmodel.navigationHandle
import kotlinx.parcelize.Parcelize

@Parcelize
data object SessionListDestination : NavigationKey.SupportsPush

class SessionListViewModel : ViewModel() {
    private val navigation by navigationHandle<SessionListDestination>()

    val sessions = Dependencies.sessionRepository.listSessions()
    val speakers = Dependencies.speakerRepository.listSpeakers()

    fun onSessionSelected(sessionId: Session.Id) {
        navigation.push(SessionDetailDestination(sessionId))
    }

    fun onAddSessionSelected() {
        navigation.push(CreateSessionDestination)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@NavigationDestination(SessionListDestination::class)
@Composable
fun SessionListScreen(
    viewModel: SessionListViewModel = viewModel()
) {
    val sessions by viewModel.sessions.collectAsState()
    val speakers by viewModel.speakers.collectAsState()

    val listState = rememberLazyListState()
    val isAtTopOfList = remember (listState){ derivedStateOf { !listState.canScrollBackward } }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            stickyHeader {
                ScreenTitle(
                    text = "Sessions",
                    showBackground = !isAtTopOfList.value,
                )
            }
            items(
                items = sessions,
                key = { it.id },
                contentType = { "SessionItem" }
            ) { session ->
                val speaker = remember { speakers.firstOrNull { it.id == session.speaker } }
                ListItem(
                    modifier = Modifier.clickable {
                        viewModel.onSessionSelected(session.id)
                    },
                    headlineContent = { Text(session.title) },
                    supportingContent = { Text(speaker?.name ?: "-") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(96.dp))
            }
        }
        ExtendedFloatingActionButton(
            onClick = { viewModel.onAddSessionSelected() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            expanded = isAtTopOfList.value,
            text = { Text("Add Session") },
            icon = { Icon(Icons.Default.BookmarkAdd, null) }
        )
    }
}