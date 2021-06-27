package com.example.wtest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wtest.data.datasource.MainPagingSource
import com.example.wtest.repository.MainRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val zipCodeMutableLivedata = MutableLiveData<Boolean>()

    val zipCodeLiveData: LiveData<Boolean>
        get() = zipCodeMutableLivedata

    fun getZipCode() {
        viewModelScope.launch {
            val result = mainRepository.getZipCode()
            zipCodeMutableLivedata.postValue(result)
        }
    }

    fun getPagedResult(query: String) =
        Pager(
            PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
            pagingSourceFactory = { MainPagingSource(query, mainRepository) }
        ).liveData

    companion object {
        private const val PAGE_SIZE = 50
    }

}