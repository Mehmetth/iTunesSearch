package com.mehmetth.itunessearch.domain.search.repository

import com.mehmetth.itunessearch.Resource
import com.mehmetth.itunessearch.data.search.dto.SearchResponse

interface SearchRepository {
    suspend fun getResults(term : String,media : String,offset : Int)  : Resource<SearchResponse>
}