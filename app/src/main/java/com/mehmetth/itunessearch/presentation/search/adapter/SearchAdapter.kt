package com.mehmetth.itunessearch.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehmetth.itunessearch.presentation.search.viewmodel.state.ContentState
import com.mehmetth.itunessearch.domain.search.model.ContentUiModel
import com.mehmetth.itunessearch.databinding.ItemResultBinding


class SearchAdapter(
    private val onItemClick: (contentState: ContentState) -> Unit) :
    PagingDataAdapter<ContentUiModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ContentUiModel>() {
            override fun areItemsTheSame(oldItem: ContentUiModel, newItem: ContentUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ContentUiModel, newItem: ContentUiModel): Boolean =
                oldItem.id == newItem.id
        }
    }

    private fun getItemViewState(position: Int) : ContentState {
        return ContentState(getItem(position)!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ResultViewHolder)?.bind(item = getItemViewState(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResultViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false),onItemClick)
    }

    class ResultViewHolder(
        private val binding: ItemResultBinding,
        private val onItemClick: (contentState: ContentState) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContentState?) {
            binding.resultState = item
            binding.root.setOnClickListener {
                item?.let {
                    onItemClick(it)
                }
            }
        }

    }


}