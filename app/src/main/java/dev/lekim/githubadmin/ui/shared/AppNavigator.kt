package dev.lekim.githubadmin.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import dev.lekim.githubadmin.domain.shared.Route
import dev.lekim.githubadmin.ui.users.userDetail.UserDetailScreen
import dev.lekim.githubadmin.ui.users.userList.UserListScreen

@Composable
fun AppNavigator(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        navigation<Route.Home>(startDestination = Route.UserList) {
            composable<Route.UserList> {
                UserListScreen { username ->
                    navController.navigate(route = Route.UserDetail(username = username))
                }
            }

            composable<Route.UserDetail> {
                val argument = it.toRoute<Route.UserDetail>()
                UserDetailScreen(username = argument.username) {
                    navController.popBackStack()
                }
            }
        }
    }
}