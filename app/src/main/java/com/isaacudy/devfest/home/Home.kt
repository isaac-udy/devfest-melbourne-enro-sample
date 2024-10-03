package com.isaacudy.devfest.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isaacudy.devfest.session.SessionListDestination
import com.isaacudy.devfest.speaker.SpeakerListDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.compose.container.rememberNavigationContainerGroup
import dev.enro.core.compose.rememberNavigationContainer
import dev.enro.core.container.EmptyBehavior
import dev.enro.core.container.acceptNone
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeDestination : NavigationKey.SupportsPush

@NavigationDestination(HomeDestination::class)
@Composable
fun HomeScreen() {
    val sessionContainer = rememberNavigationContainer(
        root = SessionListDestination,
        emptyBehavior = EmptyBehavior.CloseParent,
        filter = acceptNone(),
    )

    val speakerContainer = rememberNavigationContainer(
        root = SpeakerListDestination,
        emptyBehavior = EmptyBehavior.Action {
            sessionContainer.setActive()
            true
        },
        filter = acceptNone(),
    )
    val containerGroup = rememberNavigationContainerGroup(
        sessionContainer,
        speakerContainer,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AnimatedContent(
            modifier = Modifier.weight(1f),
            targetState = containerGroup.activeContainer,
        ) { activeContainer ->
            activeContainer.Render()
        }
        NavigationBar(
            modifier = Modifier.fillMaxWidth()
        ) {
            NavigationBarItem(
                selected = containerGroup.activeContainer == sessionContainer,
                icon = { Icon(Icons.Outlined.Bookmark, null) },
                label = { Text("Sessions") },
                onClick = {
                    sessionContainer.setActive()
                }
            )
            NavigationBarItem(
                selected = containerGroup.activeContainer == speakerContainer,
                icon = { Icon(Icons.Default.Group, null) },
                label = { Text("Speakers") },
                onClick = {
                    speakerContainer.setActive()
                }
            )
        }
    }
}
