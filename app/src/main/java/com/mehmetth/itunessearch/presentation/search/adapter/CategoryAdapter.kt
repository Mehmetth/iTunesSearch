package com.mehmetth.itunessearch.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehmetth.itunessearch.domain.search.model.CategoryType
import com.mehmetth.itunessearch.databinding.CategoryItemBinding

class CategoryAdapter(val categoryTypeList: List<CategoryType>,
                      val onItemClick: (categoryType: CategoryType) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = categoryTypeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(categoryTypeList[position])

    inner class ViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryType) {
            binding.item = item
            binding.categoryItem.setOnClickListener {
                onItemClick(item)
            }
            binding.executePendingBindings()
        }
    }
}