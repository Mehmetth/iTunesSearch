package com.mehmetth.itunessearch.presentation.search.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehmetth.itunessearch.Resource
import com.mehmetth.itunessearch.data.search.dto.ContentResponse
import com.mehmetth.itunessearch.domain.search.repository.SearchRepository
import com.mehmetth.itunessearch.presentation.search.viewmodel.state.SearchState
import com.mehmetth.itunessearch.utils.Constants

class ResultsPagingSource(
    private val searchRepository: SearchRepository,
    private val searchState: SearchState
) : PagingSource<Int, ContentResponse>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ContentResponse> {
        if(searchState.searchTerm.value.length < Constants.MIN_SEARCH_LENGTH){
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
        }
        return try {
            val oldKey = params.key ?: 0
            when(val result = searchRepository.getResults(searchState.searchTerm.value,searchState.filterMediaType.value.type,oldKey)){
                is Resource.Success -> {
                    val newKey = (result.successData.resultCount ?: 0) + oldKey
                    val nextKey : Int? = if(oldKey == newKey) null else newKey
                    LoadResult.Page(
                        data = result.successData.results,
                        prevKey = null,
                        nextKey = nextKey
                    )
                }
                is Error -> LoadResult.Error(Throwable(result.message))
                else -> LoadResult.Error(Throwable())
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ContentResponse>): Int? = null

}