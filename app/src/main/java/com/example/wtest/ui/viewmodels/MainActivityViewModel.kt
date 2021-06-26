package com.example.wtest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

}