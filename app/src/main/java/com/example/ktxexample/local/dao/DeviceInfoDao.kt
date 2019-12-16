package com.example.ktxexample.local.dao

import androidx.room.*
import com.example.ktxexample.model.response.login.LoginResponseModel

/**
 * Created by Rozina on 2019-07-09.
 */

@Dao
interface DeviceInfoDao {

    /**
     *  insert single record in NewsTags
     * @param filter is object to be insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(filter: LoginResponseModel.Response.DeviceInfo)

    /**
     * delete single record from NewsTags table
     * @param filter is object to be delete
     */
    @Delete
    fun deleteRecord(filter: LoginResponseModel.Response.DeviceInfo)

    /**
     * get all records from table NewsTags
     */
    @Query("SELECT * FROM DeviceInfo")
    fun getRecords(): List<LoginResponseModel.Response.DeviceInfo>

    @Query("DELETE FROM DeviceInfo")
    fun deleteTableRecords()
}