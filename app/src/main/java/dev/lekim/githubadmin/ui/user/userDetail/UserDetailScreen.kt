package dev.lekim.githubadmin.ui.user.userDetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import dev.lekim.githubadmin.R
import dev.lekim.githubadmin.data.user.User
import dev.lekim.githubadmin.param.user.UserGetParam
import dev.lekim.githubadmin.ui.shared.*
import dev.lekim.githubadmin.ui.user.UserCard
import dev.lekim.githubadmin.ui.shared.LoadState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    userDetailViewModel: UserDetailViewModel,
    username: String,
    canNavigateUp: Boolean,
    onNavigateUp: () -> Unit
) {
    val userDetailState by userDetailViewModel.uiState.collectAsState()
    val param = UserGetParam(username = username)
    val scrollState = rememberScrollState()

    val onRefresh: () -> Unit = {
        userDetailViewModel.viewModelScope.launch {
            userDetailViewModel.getUser(param)
        }
    }

    LaunchedEffect(Unit) {
        userDetailViewModel.viewModelScope.launch {
            userDetailViewModel.getUser(param)
        }
    }


    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "User Details") },
                navigationIcon = {
                    if (canNavigateUp) {
                        Button(onClick = onNavigateUp) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = userDetailState.loadState.isLoading,
            onRefresh = onRefresh,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .align(Alignment.Center)
            ) {
                when (userDetailState.loadState) {
                    is LoadState.Success -> {
                        userDetailState.user?.let { user ->
                            Column {
                                UserDetailCardView(user = user)
                                UserDetailFollowView(
                                    follower = Pair(
                                        R.string.follower,
                                        user.followers
                                    ),
                                    following = Pair(
                                        R.string.following,
                                        user.following
                                    ),
                                    modifier = Modifier.padding(vertical = 24.dp)
                                )
                                UserDetailBlogView(blog = user.blog)
                            }
                        }
                    }

                    is LoadState.Failure -> Text(
                        text = (userDetailState.loadState as LoadState.Failure).error.message
                    )

                    else -> LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
private fun UserDetailCardView(modifier: Modifier = Modifier, user: User) {
    UserCard(
        modifier = modifier,
        title = {
            Text(
                text = user.login,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        content = {
            user.location?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.LocationOn, contentDescription = null)
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        },
        imgUrl = user.avatarUrl
    )
}

@Composable
fun UserDetailFollowView(
    follower: Pair<Int, Int?>,
    following: Pair<Int, Int?>,
    modifier: Modifier = Modifier
) {
    val pairs = listOf(follower, following)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        pairs.map {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = when (it.first) {
                            R.string.follower -> painterResource(id = R.drawable.follower)
                            R.string.following -> painterResource(id = R.drawable.following)
                            else -> throw IllegalArgumentException("Wrong user follow argument")
                        },
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = ver4)
                Text(
                    text = it.second?.toString() ?: "-",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = ver4)
                Text(text = stringResource(id = it.first))
            }
        }
    }
}

@Composable
fun UserDetailBlogView(
    blog: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.blog),
            style = MaterialTheme.typography.headlineSmall
        )
        blog?.let {
            Spacer(modifier = ver8)
            Text(text = blog)
        }
    }
}