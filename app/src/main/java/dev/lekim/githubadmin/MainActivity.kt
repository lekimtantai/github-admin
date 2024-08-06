package dev.lekim.githubadmin

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.lekim.githubadmin.ui.navigator.AppNavigator
import dev.lekim.githubadmin.ui.theme.AppTheme

@AndroidEntryPoint
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
