package com.example.ktxexample.view.activity

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktxexample.R
import com.example.ktxexample.base.BaseActivity
import com.example.ktxexample.databinding.HomeActivityBinding
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed
import com.example.ktxexample.state.HomeScreenState
import com.example.ktxexample.utils.AppDialog
import com.example.ktxexample.utils.Log
import com.example.ktxexample.utils.ParseApiError
import com.example.ktxexample.view.adapter.FeedAdapter
import com.example.ktxexample.view.adapter.ItemTouchListener
import com.example.ktxexample.viewmodel.HomeActivityVM
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeActivityVM, HomeActivityBinding>(R.layout.activity_home),
    ItemTouchListener {

    private lateinit var feedAdapter: FeedAdapter
    override val viewModel: HomeActivityVM by viewModel()
    private val pref: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            viewModel.state().observe(this) { state ->
                renderState(state)
            }
            setRecyclerView()
        } catch (e: Exception) {
            Log.e(e.toString())
        }
    }

    private fun setRecyclerView() {
        try {
            feedAdapter = FeedAdapter(this,this)
            val layoutManager = LinearLayoutManager(this)
            feedList.layoutManager = layoutManager
            feedList.hasFixedSize()
            feedList.adapter = feedAdapter
            feedList.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        }catch (e:Exception){
            Log.e(e.toString())
        }
    }

    private fun renderState(state: HomeScreenState) {
        try {
            when (state) {
                is HomeScreenState.Initial -> {
                    showProgress()
                    viewModel.combineApiCall()
                }

                is HomeScreenState.Loading -> {
                    showProgress()
                }

                is HomeScreenState.FeedResponse -> {
                    hideProgress()
                    feedAdapter.setList(state.list)
                    Log.e("List = ${state.list.size}")
                }

                is HomeScreenState.ApiFailure -> {
                    hideProgress()
                    val errorMsg = ParseApiError.handleError(state.throwable, this)
                    AppDialog.showAlertDialog(
                        this,
                        "",
                        errorMsg,
                        "OK",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                        })
                    Log.e("LoginScreenState.ApiFailure ${state.throwable}")
                }

            }
        } catch (e: Exception) {
            Log.e(e.toString())
        }
    }

    override fun onItemClick(feed: BaseNewsFeed) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("feed",feed)
        startActivity(intent)
    }
}
