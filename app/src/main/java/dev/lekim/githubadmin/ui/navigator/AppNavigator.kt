package dev.lekim.githubadmin.ui.navigator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import dev.lekim.githubadmin.ui.user.userDetail.*
import dev.lekim.githubadmin.ui.user.userList.*

@Composable
fun AppNavigator(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        navigation<Route.Home>(startDestination = Route.UserList) {
            composable<Route.UserList> {
                val userListViewModel = hiltViewModel<UserListViewModel>()

                UserListScreen(userListViewModel = userListViewModel) { username ->
                    navController.navigate(route = Route.UserDetail(username = username))
                }
            }

            composable<Route.UserDetail> {
                val userDetailViewModel = hiltViewModel<UserDetailViewModel>()
                val argument = it.toRoute<Route.UserDetail>()
                val canNavigateUp = navController.previousBackStackEntry != null

                UserDetailScreen(
                    userDetailViewModel = userDetailViewModel,
                    username = argument.username,
                    canNavigateUp = canNavigateUp,
                    onNavigateUp = { navController.navigateUp() },
                )
            }
        }
    }
}