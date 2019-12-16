package com.example.ktxexample.model.base

import com.google.gson.annotations.SerializedName

/**
 * Created by Rozina on 2019-06-25.
 */
class APIError(
    @SerializedName("errorMsg")
    val errorMsg: String
)