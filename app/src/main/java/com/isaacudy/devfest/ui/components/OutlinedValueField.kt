package com.isaacudy.devfest.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OutlinedValueField(
    value: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    val defaultColors = OutlinedTextFieldDefaults.colors()
    val disabledEnabledColors = OutlinedTextFieldDefaults.colors(
        disabledTextColor = defaultColors.unfocusedTextColor,
        disabledContainerColor = defaultColors.unfocusedContainerColor,
        disabledLeadingIconColor = defaultColors.unfocusedLeadingIconColor,
        disabledTrailingIconColor = defaultColors.unfocusedTrailingIconColor,
        disabledLabelColor = defaultColors.unfocusedLabelColor,
        disabledPlaceholderColor = defaultColors.unfocusedPlaceholderColor,
        disabledSupportingTextColor = defaultColors.unfocusedSupportingTextColor,
        disabledPrefixColor = defaultColors.unfocusedPrefixColor,
        disabledSuffixColor = defaultColors.unfocusedSuffixColor,
    )
    OutlinedTextField(
        modifier = modifier,
        value = value,
        readOnly = true,
        enabled = false,
        onValueChange = {},
        label = if (label != null) {
            {
                Text(label)
            }
        } else null,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        colors = disabledEnabledColors,
    )
}