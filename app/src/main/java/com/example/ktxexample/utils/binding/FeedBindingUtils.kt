package com.example.ktxexample.utils.binding

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ktxexample.R
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.utils.GlideApp

object FeedBindingUtils {

    @JvmStatic
    fun createFullName(baseNewsFeed: BaseNewsFeed): String {
        if (baseNewsFeed.country != null && baseNewsFeed.country != "")
            return baseNewsFeed.country
        else
            return "India"
    }

    @JvmStatic
    fun setTitle(baseNewsFeed: BaseNewsFeed): String {
        if (baseNewsFeed.title != null && baseNewsFeed.title != "")
            return baseNewsFeed.title
        else
            return "Title"
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: AppCompatImageView, url: String, gender: String) {
        Log.e("url", "https://randomuser.me/api/portraits/women/90.jpg")
        GlideApp.with(view.context)
            .load("https://randomuser.me/api/portraits/women/90.jpg")
            .placeholder(R.drawable.ic_male_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }

    @JvmStatic
    fun setSource(baseNewsFeed: BaseNewsFeed): String {
        if (baseNewsFeed.source != null && baseNewsFeed.source != "")
            return baseNewsFeed.source
        else
            return "Source"
    }

    @JvmStatic
    fun setLink(baseNewsFeed: BaseNewsFeed): String {
        if (baseNewsFeed.link != null && baseNewsFeed.link != "")
            return baseNewsFeed.link
        else
            return "Link"
    }

    @JvmStatic
    fun setDesc(baseNewsFeed: BaseNewsFeed): String {
        if (baseNewsFeed.description != null && baseNewsFeed.description != "")
            return baseNewsFeed.description
        else
            return "Description"
    }

    @JvmStatic
    fun setPubDate(baseNewsFeed: BaseNewsFeed): String {
        if (baseNewsFeed.pubDate != null && baseNewsFeed.pubDate != "")
            return baseNewsFeed.pubDate
        else
            return "Pub Date"
    }


}