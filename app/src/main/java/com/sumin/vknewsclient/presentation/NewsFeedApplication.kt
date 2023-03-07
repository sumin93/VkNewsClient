package com.sumin.vknewsclient.presentation

import android.app.Application
import com.sumin.vknewsclient.di.ApplicationComponent
import com.sumin.vknewsclient.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
