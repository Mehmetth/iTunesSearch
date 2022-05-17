package com.mehmetth.itunessearch

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun Fragment.showToastMessage(message : String){
    Toast.makeText(
        requireContext(),
        message,
        Toast.LENGTH_LONG
    ).show()
}


fun <T> Fragment.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T)->Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}