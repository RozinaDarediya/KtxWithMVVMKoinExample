package com.example.ktxexample.view.activity

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.observe
import com.example.ktxexample.R
import com.example.ktxexample.base.BaseActivity
import com.example.ktxexample.databinding.LoginActivityBinding
import com.example.ktxexample.state.LoginScreenState
import com.example.ktxexample.utils.AppConstants
import com.example.ktxexample.utils.AppDialog
import com.example.ktxexample.utils.Log
import com.example.ktxexample.utils.ParseApiError
import com.example.ktxexample.viewmodel.LoginActivityVM
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<LoginActivityVM, LoginActivityBinding>(R.layout.activity_login) {

    override val viewModel: LoginActivityVM by viewModel()
    private val pref: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if ((pref.getString(AppConstants.pref_token, "") ?: "").isNotBlank()) {
            redirectToHome()
        } else {

        }

        etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                btnLogin.performClick()
                true
            } else
                return@setOnEditorActionListener false
        }

        //viewModel.userPreference()
        viewModel.state().observe(this) { state ->
            renderState(state)
        }
    }

    private fun redirectToHome() {
        //viewModel.combineApiCall()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun renderState(state: LoginScreenState) {
        try {
            when (state) {
                is LoginScreenState.UserPreference -> {
                    if (state.isUserLoggedIn) {
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }

                is LoginScreenState.EmailValidationError -> {
                    enableViews(true)
                    tillEmail.error = state.error
                }

                is LoginScreenState.PssswordValidatorError -> {
                    tillEmail.error = ""
                    enableViews(true)
                    tillPassword.error = state.error
                }

                is LoginScreenState.Loading -> {
                    enableViews(false)
                    tillPassword.error = ""
                    Log.e("LoginScreenState.Loading ")
                    showProgress()
                }

                is LoginScreenState.LoginSuccess -> {
                    hideProgress()
                    pref.edit().putString(
                        AppConstants.pref_token,
                        state.loginSuccess.response.deviceInfo.deviceAccessToken
                    ).apply()
                    Log.e("LoginScreenState.LoginSuccess ${state.loginSuccess}")
                    redirectToHome()
                }

                is LoginScreenState.ApiFailure -> {
                    hideProgress()
                    val errorMsg = ParseApiError.handleError(state.throwable, this)
                    AppDialog.showAlertDialog(
                        this,
                        "",
                        errorMsg,
                        "OK",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                            enableViews(true)
                        })
                    Log.e("LoginScreenState.ApiFailure ${state.throwable}")
                }

                is LoginScreenState.FeedResponse -> {
                    Log.e("List = ${state.list.size}")
                }
            }
        } catch (e: Exception) {
            Log.e(e.toString())
        }
    }

    private fun enableViews(enable: Boolean) {
        etEmail.isEnabled = enable
        etPassword.isEnabled = enable
        btnLogin.isEnabled = enable
    }
}
