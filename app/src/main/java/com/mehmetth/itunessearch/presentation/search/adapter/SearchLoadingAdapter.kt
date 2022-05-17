package com.mehmetth.itunessearch.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehmetth.itunessearch.R
import com.mehmetth.itunessearch.databinding.ItemLoadingStateBinding
import com.mehmetth.itunessearch.databinding.ItemResultBinding


class SearchLoadingAdapter(
    private val adapter : SearchAdapter
) : LoadStateAdapter<SearchLoadingAdapter.SearchLoadingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        SearchLoadingViewHolder(
            ItemLoadingStateBinding
                .inflate(
                    LayoutInflater
                        .from(parent.context), parent, false)
            )
         { adapter.retry() }

    override fun onBindViewHolder(holder: SearchLoadingViewHolder, loadState: LoadState) = holder.bind(loadState)

    class SearchLoadingViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                detailErrorMessageTitle.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                detailErrorMessage.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                detailErrorMessage.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}