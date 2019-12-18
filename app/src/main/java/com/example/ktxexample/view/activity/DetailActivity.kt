package com.example.ktxexample.view.activity

import android.os.Bundle
import androidx.lifecycle.observe
import com.example.ktxexample.R
import com.example.ktxexample.base.BaseActivity
import com.example.ktxexample.databinding.DetailActivityBinding
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.state.CommonState
import com.example.ktxexample.utils.Log
import com.example.ktxexample.viewmodel.DetailActivityVM
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

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

        imageClose.setOnClickListener {
            Log.e("imageClose")
        }
        textLink.setOnClickListener {
            Log.e("textLink")
        }

        /*viewModel.state().observe(this) { state ->
            renderState(state)
        }*/
    }

    private fun renderStatus(state: CommonState) {
        when (state) {
            is CommonState.BackClick -> {
                finish()
            }

            is CommonState.LinkClick -> {
                val url = state.linkUrl
                Log.e(url)
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
