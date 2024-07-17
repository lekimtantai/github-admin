package dev.lekim.githubadmin.ui.users.userList

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserListScreen(modifier: Modifier = Modifier, onNavigate: (String) -> Unit) {
    Scaffold(modifier = modifier) {
        TextButton(onClick = { onNavigate("lekimtantai") }) {
            Text(
                text = "Users",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(it),
            )
        }
    }
}

@Preview
@Composable
private fun UserListScreenPreview() {
    UserListScreen {}
}