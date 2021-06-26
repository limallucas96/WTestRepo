package com.example.wtest.ui.bases

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T
    protected abstract val viewModel: ViewModel

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int?
    protected abstract fun onViewReady()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        onViewReady()
    }

    private fun initBinding() {
        getContentLayoutId()?.let { binding = DataBindingUtil.setContentView(this, it) }
    }

}
