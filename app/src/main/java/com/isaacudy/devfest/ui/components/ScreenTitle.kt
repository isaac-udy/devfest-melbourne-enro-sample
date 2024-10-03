package com.isaacudy.devfest.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTitle(
    text: String,
    showBackground: Boolean,
    statusBarPadding: Boolean = true,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                animateDpAsState(if (showBackground) 4.dp else 0.dp).value
            )
            .background(
                animateColorAsState(if (showBackground) MaterialTheme.colorScheme.surfaceContainer else MaterialTheme.colorScheme.surface).value,
            )
            .let {
                when (statusBarPadding) {
                    true -> it.statusBarsPadding()
                    false -> it
                }
            }
            .padding(12.dp),
    )
}