package com.sumin.vknewsclient.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
    return merge(this, another)
}
