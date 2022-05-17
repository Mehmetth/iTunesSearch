package com.mehmetth.itunessearch.domain.search.repository

import com.mehmetth.itunessearch.data.search.repository.SearchRemoteRepository

class SearchRepositoryImpl(
    private val searchRemoteRepository: SearchRemoteRepository
) : SearchRepository {
    override suspend fun getResults(term : String,media : String,offset : Int) = searchRemoteRepository.getSearchResults(term,media,offset)
}