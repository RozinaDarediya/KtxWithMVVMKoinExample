package com.example.ktxexample.base

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.ktxexample.BR
import com.example.ktxexample.R

abstract class BaseActivity<VM : BaseVM, VDB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    abstract val viewModel: VM
    private lateinit var binding: VDB
    private var mProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeProgressDialog()

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }

    /*
    * Initialize the progress dialog
    */
    private fun initializeProgressDialog() {
        mProgressDialog = Dialog(this@BaseActivity, R.style.MyTheme)
        mProgressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mProgressDialog!!.setCancelable(false)
        //mProgressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        mProgressDialog!!.setContentView(R.layout.progress)
    }

    /*
    * shows progress
    */
    internal fun showProgress() {
        if (mProgressDialog != null && !mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }

    /*
     * dismiss progress
     */
    internal fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

}