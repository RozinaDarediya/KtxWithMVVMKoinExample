package com.example.ktxexample.state

import com.example.ktxexample.model.response.feed_model.RSS.Feed
import com.example.ktxexample.model.response.login.LoginResponseModel

sealed class LoginScreenState {

    object Initial: LoginScreenState()
    object Loading : LoginScreenState()

    data class EmailValidationError(val error: String) : LoginScreenState()
    data class PssswordValidatorError(val error: String) : LoginScreenState()

    data class UserPreference(val isUserLoggedIn:Boolean) : LoginScreenState()
    data class LoginSuccess(val loginSuccess: LoginResponseModel) : LoginScreenState()
    data class ApiFailure(val throwable: Throwable) : LoginScreenState()
    data class FeedResponse(val list:ArrayList<Feed>) : LoginScreenState()

}