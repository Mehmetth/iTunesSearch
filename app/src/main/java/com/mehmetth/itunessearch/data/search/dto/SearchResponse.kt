package com.mehmetth.itunessearch.data.search.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class SearchResponse(
    @Json(name = "resultCount")
    val resultCount: Int?,
    @Json(name = "results")
    val results: List<ContentResponse>
): Parcelable