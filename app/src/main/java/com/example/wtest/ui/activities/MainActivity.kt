package com.example.wtest.ui.activities

import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.wtest.R
import com.example.wtest.databinding.ActivityMainBinding
import com.example.wtest.ui.adapters.ListLoadStateAdapter
import com.example.wtest.ui.adapters.ZipcodeListAdapter
import com.example.wtest.ui.bases.BaseActivity
import com.example.wtest.ui.viewmodels.MainActivityViewModel
import com.example.wtest.utils.onQueryTextSubmit
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var zipcodeAdapter = ZipcodeListAdapter()

    override val viewModel by inject<MainActivityViewModel>()
    override fun getContentLayoutId() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        (menu?.findItem(R.id.action_search)?.actionView as? SearchView)?.let { searchView ->
            searchView.onQueryTextSubmit { query ->
                getPaginatedResults(query)
                searchView.clearFocus()
            }
        }

        return true
    }

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

    private fun getPaginatedResults(query: String = "") {
        zipcodeAdapter.submitData(lifecycle, PagingData.empty())
        viewModel.getPagedResult(query).observe(this@MainActivity, {
            zipcodeAdapter.submitData(lifecycle, it)
        })
    }

    private fun setupObservables() {
        viewModel.zipCodeLiveData.observe(this, { handleZipcodeResult(it) })
    }

    private fun handleZipcodeResult(didLoad: Boolean) {
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

    private fun showEmptyState() {
        binding.run {
            grLoading.isVisible = false
            rvRecyclerView.isVisible = false
            tvEmptyState.isVisible = true
        }
    }

    private fun handleEmptyList() {
        when {
            zipcodeAdapter.itemCount > 0 -> {
                Toast.makeText(this, "End of results", Toast.LENGTH_SHORT).show()
            }
            viewModel.zipCodeLiveData.value == null -> {
                showLoading()
            }
            else -> {
                showEmptyState()
            }
        }
    }

    private fun fetchZipcodes() {
        showLoading()
        viewModel.getZipCode()
    }

}