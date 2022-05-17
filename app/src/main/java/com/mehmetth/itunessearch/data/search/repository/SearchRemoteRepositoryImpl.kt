package com.mehmetth.itunessearch.data.search.repository

import com.mehmetth.itunessearch.data.search.api.SearchApi
import com.mehmetth.itunessearch.getRemoteResponse

class SearchRemoteRepositoryImpl(
    private val searchApi: SearchApi
) : SearchRemoteRepository {
    override suspend fun getSearchResults(term : String, media : String, offset : Int) = searchApi.search(term,offset,media).getRemoteResponse()
}