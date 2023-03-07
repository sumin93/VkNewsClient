package com.sumin.vknewsclient.presentation

import android.app.Application
import com.sumin.vknewsclient.di.ApplicationComponent
import com.sumin.vknewsclient.di.DaggerApplicationComponent
import com.sumin.vknewsclient.domain.entity.FeedPost

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this,
            FeedPost(0, 0, "", "", "", "", "", listOf(), false)
        )
    }
}
