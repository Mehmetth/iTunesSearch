package com.mehmetth.itunessearch.data.search.api

import com.mehmetth.itunessearch.data.search.dto.SearchResponse
import com.mehmetth.itunessearch.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search")
    suspend fun search(
        @Query("term") term : String,
        @Query("offset") offset : Int,
        @Query("media") media : String? = null,
        @Query("limit") limit : Int = Constants.PAGE_SIZE
    ) : Response<SearchResponse>
}