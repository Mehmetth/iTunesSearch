package com.mehmetth.itunessearch.presentation.search.viewmodel.state

import com.mehmetth.itunessearch.domain.search.model.ContentUiModel

class ContentState(
    private val content : ContentUiModel
) {
    fun imageUrl() = content.imageUrl
    fun name() = content.name
    fun itemDetail() = content
}