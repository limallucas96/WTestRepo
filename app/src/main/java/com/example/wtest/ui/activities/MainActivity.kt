package com.example.wtest.ui.activities

import com.example.wtest.ui.viewmodels.MainActivityViewModel
import com.example.wtest.R
import com.example.wtest.ui.bases.BaseActivity
import com.example.wtest.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity() : BaseActivity<ActivityMainBinding>() {

    override val viewModel by inject<MainActivityViewModel>()

    override fun getContentLayoutId() = R.layout.activity_main

    override fun onViewReady() {
        viewModel.getZipCode()
    }
}