package com.example.ktxexample.state

sealed class CommonState {

    object Initial : CommonState()
    object BackClick : CommonState()

    data class LinkClick(var linkUrl: String) : CommonState()

}