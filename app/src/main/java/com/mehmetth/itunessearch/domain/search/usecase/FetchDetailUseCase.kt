package com.mehmetth.itunessearch.domain.search.usecase

import androidx.lifecycle.SavedStateHandle
import com.mehmetth.itunessearch.domain.search.model.ContentUiModel
import javax.inject.Inject

class FetchDetailUseCase @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) {
    //contentUiModel => Navigation parse args data class
    operator fun invoke() = savedStateHandle.get<ContentUiModel>("contentUiModel")
}