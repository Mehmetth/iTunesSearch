package com.mehmetth.itunessearch

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.mehmetth.itunessearch.data.search.dto.ContentResponse
import com.mehmetth.itunessearch.domain.search.model.ContentUiModel
import com.mehmetth.itunessearch.domain.search.repository.SearchRepository
import com.mehmetth.itunessearch.presentation.search.adapter.ResultsPagingSource
import com.mehmetth.itunessearch.presentation.search.viewmodel.state.SearchState
import com.mehmetth.itunessearch.utils.Constants
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface Maping<T,R> {
    fun mapFromResponse(type: T) : R
}

class FetchResultsUseCase@Inject constructor(
    private val searchRepository: SearchRepository,
    private val mapperSearch : FetchSearchResultsMapper,
    private val searchState: SearchState
) {
    operator fun invoke() =
        Pager(PagingConfig(pageSize = Constants.PAGE_SIZE)) {
            ResultsPagingSource(searchRepository,searchState)
        }.flow.map { pagingData ->
            pagingData.map {
                mapperSearch.mapFromResponse(it)
            }
        }
}

class FetchSearchResultsMapper @Inject constructor(
) : Maping<ContentResponse, ContentUiModel> {
    override fun mapFromResponse(type: ContentResponse): ContentUiModel {
        return ContentUiModel(
            id = type.trackId ?: type.collectionId ?: 0,
            imageUrl = type.artworkUrl600 ?: type.artworkUrl512 ?: type.artworkUrl100,
            price = if(type.collectionPrice == null){
                ""
            }else {
                "${type.collectionPrice} ${type.currency}"
            },
            name = type.trackName ?: "",
            date = type.releaseDate,
            isStreamable = type.isStreamable ?: false,
            description = type.description,
            artistFirstWords = if(type.artistName.isNullOrEmpty()) "" else "${type.artistName.split(" ").firstOrNull()?.first() ?: ""}${type.artistName.split(" ").getOrNull(1)?.first() ?: ""}",
            artistName = type.artistName
        )
    }

}