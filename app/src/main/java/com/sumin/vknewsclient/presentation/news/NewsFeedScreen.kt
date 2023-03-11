package com.sumin.vknewsclient.presentation.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sumin.vknewsclient.domain.entity.FeedPost
import com.sumin.vknewsclient.presentation.getApplicationComponent
import com.sumin.vknewsclient.ui.theme.DarkBlue

@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: NewsFeedViewModel = viewModel(factory = component.getViewModelFactory())
    val screenState = viewModel.screenState.collectAsState(NewsFeedScreenState.Initial)

    NewsFeedScreenContent(
        screenState = screenState,
        paddingValues = paddingValues,
        onCommentClickListener = onCommentClickListener,
        viewModel = viewModel
    )
}

@Composable
private fun NewsFeedScreenContent(
    screenState: State<NewsFeedScreenState>,
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit,
    viewModel: NewsFeedViewModel,
) {
    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentClickListener = onCommentClickListener,
                nextDataIsLoading = currentState.nextDataIsLoading
            )
        }
        NewsFeedScreenState.Initial -> {}
        NewsFeedScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = DarkBlue)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentClickListener: (FeedPost) -> Unit,
    nextDataIsLoading: Boolean
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues), contentPadding = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp
        ), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = posts, key = { it.id }) { feedPost ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }

            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart)
            ) {
                PostCard(
                    feedPost = feedPost,
                    onCommentClickListener = {
                        onCommentClickListener(feedPost)
                    },
                    onLikeClickListener = { _ ->
                        viewModel.changeLikeStatus(feedPost)
                    },
                )
            }
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = DarkBlue)
                }
            } else {
                SideEffect {
                    viewModel.loadNextRecommendations()
                }
            }
        }
    }
}
