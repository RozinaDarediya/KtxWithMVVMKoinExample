package com.example.ktxexample.model.base

import com.google.gson.annotations.SerializedName

/**
 * Created by Rozina on 2019-08-01.
 */
open class BaseResponseModel (

    @SerializedName("errorMsg")
    val errorMsg: String
) {
    constructor() : this("")
}