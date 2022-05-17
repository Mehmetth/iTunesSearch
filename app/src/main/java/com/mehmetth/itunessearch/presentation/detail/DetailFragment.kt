package com.mehmetth.itunessearch.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mehmetth.itunessearch.presentation.detail.viewmodel.DetailViewModel
import com.mehmetth.itunessearch.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailViewModel : DetailViewModel by viewModels()
    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDetailState()
    }

    private fun observeDetailState(){
        lifecycleScope.launchWhenStarted {
            detailViewModel.state.collectLatest {
                binding.detailState = it
            }
        }
    }
}