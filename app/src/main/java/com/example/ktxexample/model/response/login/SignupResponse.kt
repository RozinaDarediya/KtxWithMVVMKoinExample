package com.example.ktxexample.model.response.login


import com.google.gson.annotations.SerializedName

data class SignupResponse(

    @SerializedName("errorMsg")
    val errorMsg: String,

    @SerializedName("response")
    val response: Response

) {
    data class Response(

        @SerializedName("child")
        val child: List<Any>,

        @SerializedName("createdAt")
        val createdAt: String,

        @SerializedName("email")
        val email: String,

        @SerializedName("firstName")
        val firstName: String,

        @SerializedName("gender")
        val gender: Int,

        @SerializedName("id")
        val id: String,

        @SerializedName("isActive")
        val isActive: Int,

        @SerializedName("isDeleted")
        val isDeleted: Int,

        @SerializedName("isResetPassword")
        val isResetPassword: Int,

        @SerializedName("isVerified")
        val isVerified: Int,

        @SerializedName("lastName")
        val lastName: String,

        @SerializedName("latitude")
        val latitude: Int,

        @SerializedName("longitude")
        val longitude: Int,

        @SerializedName("password")
        val password: String,

        @SerializedName("photo")
        val photo: String,

        @SerializedName("role")
        val role: String,

        @SerializedName("status")
        val status: Int,

        @SerializedName("updatedAt")
        val updatedAt: String,

        @SerializedName("__v")
        val v: Int

    )
}