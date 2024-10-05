package com.isaacudy.devfest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.isaacudy.devfest.home.HomeDestination
import com.isaacudy.devfest.ui.theme.DevFestTheme
import dev.enro.core.compose.rememberNavigationContainer
import dev.enro.core.container.EmptyBehavior

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val container = rememberNavigationContainer(
                root = HomeDestination,
                emptyBehavior = EmptyBehavior.CloseParent
            )
            DevFestTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides MaterialTheme.colorScheme.contentColorFor(
                            MaterialTheme.colorScheme.background
                        )
                    ) {
                        container.Render()
                    }
                }
            }
            LaunchedEffect(container.backstack) {
                Log.d(
                    "DevFest Sample",
                    "MainActivity Backstack: ${container.backstack.map { it.navigationKey::class.java.simpleName }}"
                )
            }
        }
    }
}
