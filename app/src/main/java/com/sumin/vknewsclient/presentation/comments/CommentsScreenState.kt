package com.sumin.vknewsclient.presentation.comments

import com.sumin.vknewsclient.domain.entity.FeedPost
import com.sumin.vknewsclient.domain.entity.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}
