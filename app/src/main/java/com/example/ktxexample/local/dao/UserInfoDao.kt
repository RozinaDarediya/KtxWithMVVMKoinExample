package com.example.ktxexample.local.dao

import androidx.room.*
import com.example.ktxexample.model.response.login.LoginResponseModel

/**
 * Created by Rozina on 2019-07-09.
 */

@Dao
interface UserInfoDao {

    /**
     *  insert single record in NewsTags
     * @param filter is object to be insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(filter: LoginResponseModel.Response.UserInfo)

    /**
     * delete single record from NewsTags table
     * @param filter is object to be delete
     */
    @Delete
    fun deleteRecord(filter: LoginResponseModel.Response.UserInfo)

    /**
     * get all records from table NewsTags
     */
    @Query("SELECT * FROM UserInfo")
    fun getRecords(): List<LoginResponseModel.Response.UserInfo>

    @Query("DELETE FROM UserInfo")
    fun deleteTableRecords()
}