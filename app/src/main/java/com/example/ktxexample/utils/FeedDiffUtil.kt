package com.example.ktxexample.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed

class FeedDiffUtil(private val newList: ArrayList<BaseNewsFeed>? = null, private val oldList: ArrayList<BaseNewsFeed>? = null) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.id == newList?.get(newItemPosition)?.id
    }

    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition) === newList?.get(newItemPosition)
    }
}
