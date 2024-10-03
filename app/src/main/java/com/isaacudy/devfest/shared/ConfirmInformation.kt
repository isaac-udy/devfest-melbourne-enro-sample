package com.isaacudy.devfest.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isaacudy.devfest.ui.components.ScreenTitle
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.closeWithResult
import dev.enro.core.compose.navigationHandle
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmInformationDestination(
    val title: String,
    val content: String,
) : NavigationKey.SupportsPush.WithResult<Unit>


@NavigationDestination(ConfirmInformationDestination::class)
@Composable
fun ConfirmInformationScreen() {
    val navigation = navigationHandle<ConfirmInformationDestination>()
    Column {
        ScreenTitle(
            text = navigation.key.title,
            showBackground = false,
        )
        Text(
            text = navigation.key.content,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp),
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                navigation.closeWithResult(Unit)
            }
        ) {
            Text("Submit")
        }
    }
}