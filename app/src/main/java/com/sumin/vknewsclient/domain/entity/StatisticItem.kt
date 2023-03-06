package com.sumin.vknewsclient.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatisticItem(
    val type: StatisticType,
    val count: Int = 0
) : Parcelable

enum class StatisticType {
    VIEWS, COMMENTS, SHARES, LIKES
}
