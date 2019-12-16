package com.example.ktxexample.model.response.login

import androidx.room.*
import com.example.ktxexample.model.base.BaseResponseModel
import com.example.ktxexample.model.base.Group
import com.example.ktxexample.model.base.Photo
import com.example.ktxexample.model.base.Role
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(

    @SerializedName("response")
    val response: Response

) : BaseResponseModel() {
    data class Response(

        @SerializedName("deviceInfo")
        val deviceInfo: DeviceInfo,

        @SerializedName("userInfo")
        val userInfo: UserInfo
    ) {

        @Entity(tableName = "DeviceInfo")
        data class DeviceInfo(

            @SerializedName("createdAt")
            val createdAt: String? = null,

            @SerializedName("device_access_token")
            val deviceAccessToken: String? = null,

            @SerializedName("device_model")
            val deviceModel: String? = null,

            @SerializedName("device_platform")
            val devicePlatform: String? = null,

            @SerializedName("device_token")
            val deviceToken: String? = null,

            @PrimaryKey
            @SerializedName("device_unique_id")
            val deviceUniqueId: String,

            @SerializedName("_id")
            val id: String? = null,

            @SerializedName("is_login")
            val isLogin: Int? = 0,

            @SerializedName("os")
            val os: String? = null,

            @SerializedName("updatedAt")
            val updatedAt: String? = null,

            @SerializedName("userId")
            val userId: String? = null,

            @SerializedName("__v")
            val v: Int? = 0
        )

        @Entity(tableName = "UserInfo",indices = [Index(value = ["userID"], unique = true)])
        data class UserInfo(

            @SerializedName("arrivalDate")
            val arrivalDate: String? = null,

            @SerializedName("createdAt")
            val createdAt: String? = null,

            @SerializedName("dateOfBirth")
            val dateOfBirth: String? = null,

            @SerializedName("departureDate")
            val departureDate: String? = null,

            @SerializedName("email")
            val email: String,

            @SerializedName("email1")
            val email1: String? = null,

            @SerializedName("email2")
            val email2: String? = null,

            @SerializedName("erCoverage")
            val erCoverage: Int? = null,

            @SerializedName("firstName")
            val firstName: String? = null,

            @SerializedName("gender")
            val gender: Int? = 0,

            @Embedded(prefix = "group")
            @SerializedName("group")
            var group: Group? = null,

            @Embedded(prefix = "photo")
            @SerializedName("photo")
            val photo: Photo? = null,

            @Embedded(prefix = "role")
            @SerializedName("role")
            val role: Role? = null,

            @PrimaryKey
            @SerializedName("id")
            @ColumnInfo(name = "userID")
            var userID: String,

            @SerializedName("insuranceType")
            val insuranceType: String? = null,

            @SerializedName("isActive")
            val isActive: Int? = 0,

            @SerializedName("isDeleted")
            val isDeleted: Int? = 0,

            @SerializedName("isResetPassword")
            var isResetPassword: Int? = 0,

            @SerializedName("isVerified")
            val isVerified: Int? = 0,

            @SerializedName("lastName")
            val lastName: String? = null,

            @SerializedName("latitude")
            val latitude: Int? = 0,

            @SerializedName("longitude")
            val longitude: Int? = 0,

            @SerializedName("passport")
            val passport: String? = null,

            @SerializedName("password")
            val password: String? = null,

            @SerializedName("passwordResetToken")
            val passwordResetToken: String? = null,

            @SerializedName("phone")
            val phone: String? = null,

            @SerializedName("localNumberIsrael")
            val localNumberIsrael: String? = null,

            @SerializedName("telemedicine")
            val telemedicine: Int? = 0,

            @SerializedName("updatedAt")
            val updatedAt: String? = null,

            @SerializedName("v")
            val __v: Int? = 0,

            @SerializedName("status")
            var status: Int? = 0,

            @SerializedName("isOnDemand")
            var isOnDemand: Int = 0
        )
    }
}
