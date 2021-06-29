package com.example.wtest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtest.data.entities.Zipcode
import com.example.wtest.databinding.ItemZipcodeViewHolderBinding

class ZipcodeListAdapter() :
    PagingDataAdapter<Zipcode, ZipcodeListAdapter.ZipcodeViewHolder>(ZIPCODE_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZipcodeViewHolder {
        val binding = ItemZipcodeViewHolderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ZipcodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZipcodeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ZipcodeViewHolder(private val binding: ItemZipcodeViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Zipcode) {
            binding.tvZipcode.text = HtmlCompat.fromHtml(item.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    companion object {
        private val ZIPCODE_DIFF = object : DiffUtil.ItemCallback<Zipcode>() {

            override fun areItemsTheSame(oldItem: Zipcode, newItem: Zipcode) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Zipcode, newItem: Zipcode) =
                oldItem == newItem

        }
    }
}
