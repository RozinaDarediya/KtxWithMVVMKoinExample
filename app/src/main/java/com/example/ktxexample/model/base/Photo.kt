package com.example.ktxexample.model.base

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Rozina on 2019-07-24.
 */

class Photo(

        @SerializedName("id")
        var id: String? = null,

        @ColumnInfo(name = "userPId")
        var userPId: String? = null,

        @SerializedName("createdAt")
        var createdAt: String? = null,

        @SerializedName("destination")
        var destination: String? = null,

        @SerializedName("file_name")
        var fileName: String? = null,

        @SerializedName("original_name")
        var originalName: String? = null,

        @SerializedName("path")
        var path: String? = null,

        @SerializedName("size")
        var size: Int? = 0,

        @SerializedName("updatedAt")
        var updatedAt: String? = null,

        @SerializedName("__v")
        var v: Int? = 0
):Serializable