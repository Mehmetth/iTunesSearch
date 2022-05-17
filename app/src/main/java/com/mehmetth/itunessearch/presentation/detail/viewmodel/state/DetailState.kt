package com.mehmetth.itunessearch.presentation.detail.viewmodel.state

import android.os.Build
import android.text.Html
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.mehmetth.itunessearch.domain.search.model.ContentUiModel
import com.mehmetth.itunessearch.presentation.detail.DetailFragment


class DetailState(
    private val contentUiModel: ContentUiModel?
){
    fun artistName() = contentUiModel?.artistName

    fun releaseDate() = contentUiModel?.date

    fun name() = contentUiModel?.name

    fun price() = contentUiModel?.price

    fun imageUrl() = contentUiModel?.imageUrl

    fun visibility() = if(contentUiModel?.description == null) View.VISIBLE else View.GONE

    fun getDescr() = contentUiModel?.description?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(it)
        }
    }

    fun onBackClicked(view : View){
        view.findFragment<DetailFragment>().findNavController().popBackStack()
    }
}