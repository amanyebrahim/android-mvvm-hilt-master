package com.example.mvvmdagger.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Feed(
        var RSRP: Int,
        var RSRQ: Int,
        var SINR: Int

) : Parcelable
