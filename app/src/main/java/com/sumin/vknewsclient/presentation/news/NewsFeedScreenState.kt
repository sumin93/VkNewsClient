package com.sumin.vknewsclient.presentation.news

import com.sumin.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(
        val posts: List<FeedPost>,
        val nextDataIsLoading: Boolean = false
    ) : NewsFeedScreenState()
}
