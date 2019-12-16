package com.example.ktxexample.model.base

import androidx.room.TypeConverters
import com.example.ktxexample.local.converter.StringTypeConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Rozina on 2019-07-24.
 */
class Group(

    @SerializedName("createdAt")
    var createdAt: String? = null,

    @SerializedName("arrivalDate")
    var arrivalDate: String? = null,

    @SerializedName("departureDate")
    var departureDate: String? = null,

    @TypeConverters(StringTypeConverter::class)
    @SerializedName("destination")
    var destination: List<String>? = null,

    @SerializedName("groupCode")
    var groupCode: String? = null,

    @SerializedName("groupName")
    var groupName: String? = null,

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("isDeleted")
    var deletedIs: Int? = 0,

    @SerializedName("status")
    var status: Int? = 0,

    @SerializedName("updatedAt")
    var updatedAt: String? = null,

    @SerializedName("subGroupName")
    var subGroupName: String? = null,

    @SerializedName("__v")
    var v: Int? = 0
):Serializable
