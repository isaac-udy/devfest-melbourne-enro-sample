package com.isaacudy.devfest.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.enro.core.compose.navigationHandle

/**
 * Enro allows you to provide a "ComposeEnvironment" as part of application configuration, which is a Composable that will wrap
 * all Composable destinations which are rendered by Enro. This allows common configuration, such as project specific
 * CompositionLocals to be applied to all destinations.
 *
 * For this example project, we're just going to wrap all pushed screens in a Box with the fillMaxSize modifier, and a
 * background color that matches the theme.
 */
@Composable
fun DevFestComposeEnvironment(
    content: @Composable () -> Unit
) {
    val navigation = navigationHandle()
    val isPushed = remember {
        navigation.instruction.navigationDirection == dev.enro.core.NavigationDirection.Push
    }

    if (isPushed) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            content()
        }
    } else {
        content()
    }
}