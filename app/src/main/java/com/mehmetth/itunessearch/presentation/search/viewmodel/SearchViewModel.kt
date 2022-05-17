package com.mehmetth.itunessearch.presentation.search.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mehmetth.itunessearch.FetchResultsUseCase
import com.mehmetth.itunessearch.domain.search.model.CategoryType
import com.mehmetth.itunessearch.presentation.search.viewmodel.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class SearchViewModel @Inject constructor(
    fetchResultsUseCase: FetchResultsUseCase,
    searchState : SearchState
) : ViewModel() {

    private val _refreshAdapterState = MutableSharedFlow<Unit>(replay = 0)
    val refreshAdapterState = _refreshAdapterState.asSharedFlow()

    private val _state = MutableStateFlow(searchState)
    val state = _state.asStateFlow()

    val flow = fetchResultsUseCase().cachedIn(viewModelScope)

    init {
        listenSearchTerm()
        listenFilterMedia()
    }

    private fun listenFilterMedia(){
        viewModelScope.launch {
            _state.value.filterMediaType.collectLatest {
                this@SearchViewModel.triggerRefreshAdapter()
            }
        }
    }

    private fun listenSearchTerm(){
        viewModelScope.launch {
            _state.value.searchTerm.collectLatest {
                this@SearchViewModel.triggerRefreshAdapter()
            }
        }
    }

    private fun triggerRefreshAdapter() {
        viewModelScope.launch {
            _refreshAdapterState.emit(Unit)
        }
    }

}