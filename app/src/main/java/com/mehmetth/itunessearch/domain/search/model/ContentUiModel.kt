package com.mehmetth.itunessearch.domain.search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentUiModel(
    val id : Int,
    val imageUrl : String,
    val price : String,
    val name : String,
    val date : String,
    val isStreamable : Boolean,
    val description : String?,
    val artistFirstWords : String?,
    val artistName : String?
) : Parcelable