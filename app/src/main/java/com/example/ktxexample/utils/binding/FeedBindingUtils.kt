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
        val a = "Country" + " " + let { baseNewsFeed.country }
        return "Country" + " " + let { baseNewsFeed.country }
    }

    @JvmStatic
    fun setTitle(baseNewsFeed: BaseNewsFeed): String {
        return baseNewsFeed.title
    }

    /*@JvmStatic
    @BindingAdapter("app:imageUrl", "app:gender")
    fun loadImage(view: AppCompatImageView) {
        GlideApp.with(view.context)
            .load("https://randomuser.me/api/portraits/women/90.jpg")
            .placeholder(R.drawable.ic_female_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }*/

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

}