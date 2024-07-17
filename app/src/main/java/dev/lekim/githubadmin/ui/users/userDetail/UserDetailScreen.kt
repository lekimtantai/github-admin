package dev.lekim.githubadmin.ui.users.userDetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun UserDetailScreen(modifier: Modifier = Modifier, username: String, onNavigate: () -> Unit) {
    Scaffold(modifier = modifier) {
        TextButton(onClick = onNavigate) {
            Text(
                text = "User Detail with $username",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(it),
            )
        }
    }
}