package com.example.ktxexample.model.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Rozina on 2019-07-24.
 */
class Role(

    @SerializedName("createdAt")
    var createdAt: String? = null,

    @SerializedName("_id")
    var id: String? = null,

    @SerializedName("isDeleted")
    var deletedIs: Int? = 0,

    @SerializedName("role_name")
    var roleName: String? = null,

    @SerializedName("slug")
    var slug: String? = null,

    @SerializedName("status")
    var status: Int? = 0,

    @SerializedName("updatedAt")
    var updatedAt: String? = null
):Serializable
