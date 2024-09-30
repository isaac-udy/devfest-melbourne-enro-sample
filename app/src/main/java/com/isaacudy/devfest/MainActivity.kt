package com.isaacudy.devfest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.isaacudy.devfest.ui.theme.DevFestTheme
import dev.enro.core.compose.rememberNavigationContainer
import dev.enro.core.container.EmptyBehavior


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val container = rememberNavigationContainer(
                root = HomeDestination(),
                emptyBehavior = EmptyBehavior.CloseParent
            )
            DevFestTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    container.Render()
                }
            }
        }
    }
}
