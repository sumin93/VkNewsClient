package com.sumin.vknewsclient.domain.usecases

import com.sumin.vknewsclient.domain.entity.FeedPost
import com.sumin.vknewsclient.domain.repository.NewsFeedRepository

class DeletePostUseCase(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.deletePost(feedPost)
    }
}
