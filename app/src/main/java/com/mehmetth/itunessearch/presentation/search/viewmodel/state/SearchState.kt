package com.mehmetth.itunessearch.presentation.search.viewmodel.state

import android.view.View
import com.mehmetth.itunessearch.domain.search.model.CategoryType
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchState @Inject constructor(){
    var searchTerm = MutableStateFlow("")
    var filterMediaType = MutableStateFlow(CategoryType.Movies)
    var noItemDefaultVisibility = View.VISIBLE
}