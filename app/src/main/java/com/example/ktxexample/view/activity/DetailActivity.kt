package com.example.ktxexample.view.activity

import android.os.Bundle
import androidx.lifecycle.observe
import com.example.ktxexample.base.BaseActivity
import com.example.ktxexample.databinding.DetailActivityBinding
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.state.CommonState
import com.example.ktxexample.utils.Log
import com.example.ktxexample.viewmodel.DetailActivityVM
import org.koin.android.viewmodel.ext.android.viewModel
import android.content.Intent
import android.net.Uri
import com.example.ktxexample.R

class DetailActivity :
    BaseActivity<DetailActivityVM, DetailActivityBinding>(R.layout.activity_detail) {

    lateinit var feed: BaseNewsFeed
    override val viewModel: DetailActivityVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentData()
        viewModel.state().observe(this) { state ->
            renderStatus(state)
        }
    }

    private fun renderStatus(state: CommonState) {
        when (state) {
            is CommonState.BackClick -> {
                finish()
            }

            is CommonState.LinkClick -> {
                try {
                    val url: String = state.linkUrl.trim()
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                    Log.e(url)
                } catch (e: Exception) {
                    Log.e(e.toString())
                }
            }
        }
    }

    private fun getIntentData() {
        try {
            feed = intent.getSerializableExtra("feed") as BaseNewsFeed
            binding.feed = feed
            viewModel.setFeed(feed)
        } catch (e: Exception) {
            Log.e(e.toString())
        }
    }
}
