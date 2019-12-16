package com.example.ktxexample.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ktxexample.R
import com.example.ktxexample.local.converter.StringTypeConverter
import com.example.ktxexample.local.dao.DeviceInfoDao
import com.example.ktxexample.local.dao.UserInfoDao
import com.example.ktxexample.model.response.login.LoginResponseModel
import com.example.ktxexample.utils.Log


/**
 * Created by Rozina on 2019-06-25.
 */

@Database(
    entities = [
        LoginResponseModel.Response.DeviceInfo::class,
        LoginResponseModel.Response.UserInfo::class
    ],
    version = 1
)
@TypeConverters(
    *[
        StringTypeConverter::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao
    abstract fun deviceInfo(): DeviceInfoDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            val db_name =
                context.getString(R.string.app_name).toLowerCase() + "_db"
            Log.e("da_name : $db_name")
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE =

                        Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            db_name
                        )
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }

}