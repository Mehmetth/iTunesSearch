package com.mehmetth.itunessearch.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.mehmetth.itunessearch.*
import com.mehmetth.itunessearch.databinding.FragmentSearchBinding
import com.mehmetth.itunessearch.domain.search.model.CategoryType
import com.mehmetth.itunessearch.presentation.search.adapter.CategoryAdapter
import com.mehmetth.itunessearch.presentation.search.adapter.SearchAdapter
import com.mehmetth.itunessearch.presentation.search.adapter.SearchLoadingAdapter
import com.mehmetth.itunessearch.presentation.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding : FragmentSearchBinding

    private lateinit var resultsAdapter : SearchAdapter

    private val categoryTypeList : MutableList<CategoryType> = mutableListOf(
        CategoryType.Movies,
        CategoryType.Music,
        CategoryType.Apps,
        CategoryType.Books
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerview()
        observeProgressBar()
        observeResultStatu()
        observeResultError()
        submitDataAdapter()
        observeSearchState()
        observeSearchAdapterUpdate()

        handleSearchView()
        initializeCategoryRecyclerview()
    }

    private fun handleSearchView(){
        binding.searchItem.isSubmitButtonEnabled = true
        binding.searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.state.value.searchTerm.value = query.toString()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
    private fun initializeCategoryRecyclerview(){
        binding.categoryItems.apply {
            adapter = CategoryAdapter(categoryTypeList){ item ->
                searchViewModel.state.value.filterMediaType.value = item
            }
        }
    }

    private fun initializeRecyclerview(){
        binding.recyclerView.apply {
            resultsAdapter = SearchAdapter(){item ->
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(item.itemDetail())
                requireView().findNavController().navigate(action)
            }
            val loadingAdapter = SearchLoadingAdapter(resultsAdapter)
            adapter = resultsAdapter.withLoadStateFooter(footer = loadingAdapter)
        }
    }
    private fun observeSearchState(){
        collectLatestLifecycleFlow(searchViewModel.state){
            binding.searchState = it
        }
    }
    private fun observeSearchAdapterUpdate(){
        collectLatestLifecycleFlow(searchViewModel.refreshAdapterState){
            this@SearchFragment.refreshAdapter()
        }
    }
    private fun refreshAdapter(){
        resultsAdapter.refresh()
    }
    private fun submitDataAdapter(){
        collectLatestLifecycleFlow(searchViewModel.flow){ pagingData->
            resultsAdapter.submitData(viewLifecycleOwner.lifecycle,pagingData)
        }
    }
    private fun observeResultError(){
        collectLatestLifecycleFlow( resultsAdapter.loadStateFlow
            .distinctUntilChangedBy { it.refresh }
            .filter { it.refresh is LoadState.Error }) {
            (it.refresh as? LoadState.Error)?.error?.message?.let { message->
                if(message.isNotEmpty()){
                    this@SearchFragment.showToastMessage(message)
                }
            }
        }
    }
    private fun observeProgressBar(){
        collectLatestLifecycleFlow( resultsAdapter.loadStateFlow
            .distinctUntilChangedBy { it.refresh }
            .map  { it.refresh is LoadState.Loading }) {
            if(it){
                binding.searchProgressBar.visibility = View.VISIBLE
            }
            else{
                binding.searchProgressBar.visibility = View.INVISIBLE
            }
        }
    }
    private fun observeResultStatu(){
        collectLatestLifecycleFlow(resultsAdapter.loadStateFlow
            .distinctUntilChangedBy { it.refresh }
            .filter { it.refresh is LoadState.NotLoading }
            .map { resultsAdapter.itemCount == 0 }) {
            binding.searchState?.noItemDefaultVisibility = if(it) View.VISIBLE else View.GONE
            binding.noValue.isVisible   = it
        }
    }
}