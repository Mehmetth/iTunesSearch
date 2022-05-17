package com.mehmetth.itunessearch.data.search.repository

import com.mehmetth.itunessearch.Resource
import com.mehmetth.itunessearch.data.search.dto.SearchResponse

interface SearchRemoteRepository {
    suspend fun getSearchResults(term : String, media : String, offset : Int) : Resource<SearchResponse>
}