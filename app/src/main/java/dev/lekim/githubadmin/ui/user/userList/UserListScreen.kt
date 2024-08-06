package dev.lekim.githubadmin.ui.user.userList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import dev.lekim.githubadmin.R
import dev.lekim.githubadmin.data.user.UserItem
import dev.lekim.githubadmin.param.user.UserGetListParam
import dev.lekim.githubadmin.ui.shared.LoadState
import dev.lekim.githubadmin.ui.user.UserCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    userListViewModel: UserListViewModel,
    onNavigate: (String) -> Unit = {},
) {
    val userListState by userListViewModel.uiState.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()

    val shouldIncreasePage = remember {
        derivedStateOf {
            if (userListState.loadState.isSuccess && lazyListState.layoutInfo.totalItemsCount > 4) {
             !lazyListState.canScrollForward && (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) >= lazyListState.layoutInfo.totalItemsCount - 4
            } else {
                false
            }
        }
    }

    val onRefresh: () -> Unit = {
        userListViewModel.viewModelScope.launch {
            userListViewModel.onRefresh()
        }
    }

    LaunchedEffect(key1 =  shouldIncreasePage.value){
        if (shouldIncreasePage.value) {
            userListViewModel.increasePageNumber()
        }
    }

    Scaffold(modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.github_users)
                )
            })
        }) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = userListState.loadState.isLoading,
            onRefresh = onRefresh,
            Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                if (userListState.loadState.isLoading) {
                    item {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }

                when (userListState.loadState) {
                    is LoadState.Failure -> item {
                        Text(text = (userListState.loadState as LoadState.Failure).error.message)
                    }
                    else -> {
                        if (userListState.users.isNotEmpty()) {
                            items(items = userListState.users,
                                key = { it.login }) { user ->
                                UserItemCard(
                                    user = user,
                                    onNavigate = onNavigate,
                                )
                            }
                        }
                    }
                }

                if (userListState.loadState.isLoadMore) {
                    item {
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
private fun UserItemCard(
    modifier: Modifier = Modifier, user: UserItem,
    onNavigate: (String) -> Unit
) {
    UserCard(
        modifier = modifier,
        title = {
            Text(
                text = user.login, style = MaterialTheme.typography.titleLarge
            )
        },
        content = {
            Text(buildAnnotatedString {
                withLink(
                    LinkAnnotation.Url(
                        user.htmlUrl, TextLinkStyles(style = SpanStyle(color = Color.Blue))
                    )
                ) { append(user.htmlUrl) }
            })
        },
        imgUrl = user.avatarUrl,
        onNavigate = { onNavigate(user.login) },
    )
}
