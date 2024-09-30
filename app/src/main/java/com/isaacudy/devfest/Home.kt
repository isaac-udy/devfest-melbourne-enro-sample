package com.isaacudy.devfest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import kotlinx.parcelize.Parcelize

@Parcelize
class HomeDestination : NavigationKey.SupportsPush

@NavigationDestination(HomeDestination::class)
@Composable
fun HomeScreen() {
    Text("Home")
}
