package com.example.wtest.ui.activities

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.example.wtest.ui.viewmodels.MainActivityViewModel
import com.example.wtest.R
import com.example.wtest.ui.bases.BaseActivity
import com.example.wtest.databinding.ActivityMainBinding
import com.example.wtest.ui.adapters.ListLoadStateAdapter
import com.example.wtest.ui.adapters.ZipcodeListAdapter
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var zipcodeAdapter = ZipcodeListAdapter()

    override val viewModel by inject<MainActivityViewModel>()
    override fun getContentLayoutId() = R.layout.activity_main

    override fun onViewReady() {
        fetchZipcodes()
        setupObservables()
        getPaginatedResults()
        zipcodeAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) showLoading() else showRecyclerView()
            if (loadState.refresh is LoadState.NotLoading &&
                loadState.append is LoadState.NotLoading
                && loadState.append.endOfPaginationReached
            ) handleEmptyList()
        }
    }

    private fun getPaginatedResults() {
        viewModel.getPagedResult().observe(this@MainActivity, {
            zipcodeAdapter.submitData(lifecycle, it)
        })
    }

    private fun setupObservables() {
        viewModel.zipCodeLiveData.observe(this, { handleZipcodeResult(it) })
    }

    private fun handleZipcodeResult(didLoad: Boolean) {
        Log.d("--", didLoad.toString())
        if (didLoad) {
            binding.rvRecyclerView.adapter = zipcodeAdapter.withLoadStateFooter(ListLoadStateAdapter())
            showRecyclerView()
        }
    }

    private fun showRecyclerView() {
        binding.run {
            grLoading.isVisible = false
            rvRecyclerView.isVisible = true
            tvEmptyState.isVisible = false
        }
    }

    private fun showLoading() {
        binding.run {
            grLoading.isVisible = true
            rvRecyclerView.isVisible = false
            tvEmptyState.isVisible = false
        }
    }

    private fun handleEmptyList() {
       if(zipcodeAdapter.itemCount > 1) {
           Toast.makeText(this, "End of results", Toast.LENGTH_SHORT).show()
       } else {
           binding.run {
               grLoading.isVisible = false
               rvRecyclerView.isVisible = false
               tvEmptyState.isVisible = true
           }
       }
    }

    private fun fetchZipcodes() {
        showLoading()
        viewModel.getZipCode()
    }

}