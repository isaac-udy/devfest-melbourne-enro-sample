@file:Suppress("RedundantSuspendModifier", "UNUSED_PARAMETER", "UNUSED_VARIABLE", "unused")

package com.isaacudy.devfest.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.isaacudy.devfest.ui.components.ScreenTitle
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.closeWithResult
import dev.enro.core.compose.navigationHandle
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestStringDestination(
    val title: String,
    val description: String? = null,
    val label: String? = null,
    val multiline: Boolean = false,
) : NavigationKey.SupportsPush.WithResult<String>, NavigationKey.SupportsPresent.WithResult<String>

@NavigationDestination(RequestStringDestination::class)
@Composable
fun RequestStringScreen() {
    val navigation = navigationHandle<RequestStringDestination>()
    val currentText = rememberSaveable { mutableStateOf("") }
    Column {
        ScreenTitle(
            text = navigation.key.title,
            showBackground = false,
        )
        navigation.key.description?.let { description ->
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp),
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = currentText.value,
            label = if (navigation.key.label != null) {
                { Text(navigation.key.label ?: "") }
            } else null,
            onValueChange = { currentText.value = it },
            singleLine = !navigation.key.multiline,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { navigation.closeWithResult(currentText.value) }
            )
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                navigation.closeWithResult(currentText.value)
            }
        ) {
            Text("Submit")
        }
    }
}
