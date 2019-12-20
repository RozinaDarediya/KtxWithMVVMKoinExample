package com.example.ktxexample.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ktxexample.R
import com.example.ktxexample.databinding.ItemUserBinding
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.utils.FeedDiffUtil
import com.example.ktxexample.utils.GlideApp
import kotlinx.android.synthetic.main.feed_item.view.*

class FeedAdapter(var content: Context,_listener: ItemTouchListener) : RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private val list: ArrayList<BaseNewsFeed> = ArrayList()
    private var listener: ItemTouchListener = _listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val mUserBinding: ItemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.feed_item, parent, false)
        return UserViewHolder(mUserBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        var clickListener: View.OnClickListener = View.OnClickListener {
            listener.onItemClick(list[holder.adapterPosition])
        }
        holder as UserViewHolder
        Log.e("url", "https://randomuser.me/api/portraits/women/90.jpg")
        GlideApp.with(content)
            .load("https://randomuser.me/api/portraits/women/90.jpg")
            .placeholder(R.drawable.ic_male_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.itemView.image)
        holder.setUser(list[position])
        holder.setClickListener(clickListener)
    }

    fun setList(newList: ArrayList<BaseNewsFeed>) {
        val diffResult = DiffUtil.calculateDiff(FeedDiffUtil(newList, list))
        diffResult.dispatchUpdatesTo(this)
        list.clear()
        list.addAll(newList)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

class UserViewHolder(var mUserBinding: ItemUserBinding) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(mUserBinding.root) {

    fun setUser(user: BaseNewsFeed) {
        mUserBinding.feed = user
    }

    fun setClickListener(clickListener: View.OnClickListener) {
        mUserBinding.root.setOnClickListener(clickListener)
    }
}

interface ItemTouchListener {
    fun onItemClick(feed: BaseNewsFeed)
}
