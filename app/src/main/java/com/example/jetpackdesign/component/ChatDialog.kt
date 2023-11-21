package com.example.jetpackdesign.component

import android.app.KeyguardManager.KeyguardDismissCallback
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun FunctionalityDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Close", style = MaterialTheme.typography.bodySmall)
            }
        },
        title = { Text(text = "Jetpack Design App", style = MaterialTheme.typography.bodyMedium) },
        text = { Text(text = "Coming soon..", style = MaterialTheme.typography.bodyMedium) },
    )
}