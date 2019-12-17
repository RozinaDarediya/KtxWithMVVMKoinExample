package com.example.ktxexample.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ktxexample.model.response.feed_model.BaseNewsFeed

/**
 * Created by Rozina on 2019-07-01.
 */

@Dao
interface NewsFeedDao {

    /**
     *  insert single record in NewsTags
     * @param filter is object to be insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(feed: BaseNewsFeed)

    /**
     *  insert single record in NewsTags
     * @param filter is object to be insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecordList(feed: List<BaseNewsFeed>)

    /**
     * get all records from table NewsTags
     */
    @Query("SELECT * FROM BaseNewsFeed ORDER BY subType , mainType ASC,feedUpdatedAt DESC,feedPubDate DESC")
    fun getFeedRecords(): List<BaseNewsFeed>

    @Query("SELECT * FROM BaseNewsFeed WHERE mainType = :id and country = :country ORDER BY feedUpdatedAt DESC,feedPubDate DESC")
    fun getRecords(id: Int, country: String): List<BaseNewsFeed>

    @Query("SELECT * FROM BaseNewsFeed WHERE mainType = :id and country = :country ORDER BY subType , mainType, feedUpdatedAt DESC,feedPubDate DESC")
    fun getWeatherRecords(id: Int, country: String): List<BaseNewsFeed>

    @Query("SELECT * FROM BaseNewsFeed WHERE mainType = :id ORDER BY feedUpdatedAt DESC,feedPubDate DESC")
    fun getRecords1(id: Int): List<BaseNewsFeed>


    @Query("DELETE FROM BaseNewsFeed")
    fun deleteTableRecords()

    @Query("select * from BaseNewsFeed where BaseNewsFeed.country = :data order by BaseNewsFeed.country ")
    fun getNewsFeedOfCountry(data: String): List<BaseNewsFeed>

}