package com.dean.anothergit.following

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataFollowing(
        var username: String? = null,
        var name: String? = null,
        var avatar: String? = null
): Parcelable