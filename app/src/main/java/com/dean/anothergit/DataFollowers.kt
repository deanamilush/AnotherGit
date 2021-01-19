package com.dean.anothergit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataFollowers(
        var username: String? = null,
        var name: String? = null,
        var avatar: String? = null
): Parcelable