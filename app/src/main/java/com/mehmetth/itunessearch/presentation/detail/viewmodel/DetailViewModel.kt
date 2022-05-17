package com.mehmetth.itunessearch.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.mehmetth.itunessearch.presentation.detail.viewmodel.state.DetailState
import com.mehmetth.itunessearch.domain.search.usecase.FetchDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    fetchDetailUseCase: FetchDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState(fetchDetailUseCase()))
    val state = _state.asStateFlow()

}