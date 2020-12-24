package com.example.mvvmdagger.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
class Feed (

//    @Transient
//  val id: Long = 0,
     var status: String,
     var totalResults: Long = 0

) : Parcelable {
}