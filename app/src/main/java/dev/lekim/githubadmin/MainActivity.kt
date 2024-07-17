package dev.lekim.githubadmin

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import dev.lekim.githubadmin.ui.shared.AppNavigator
import dev.lekim.githubadmin.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                AppNavigator()
            }
        }
    }
}
