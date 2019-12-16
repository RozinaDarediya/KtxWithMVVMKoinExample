package com.example.ktxexample.model.response.feed_model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ktxexample.local.converter.DateConvertersJava;

import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by Rozina on 2019-07-06.
 */
@Entity(tableName = "BaseNewsFeed")
public class BaseNewsFeed implements Serializable {

    @ColumnInfo(name = "description")
    @Element(name = "description")
    public String description;

    @ColumnInfo(name = "source")
    @Element(name = "source")
    public String source;

    @ColumnInfo(name = "title")
    @Element(name = "title")
    public String title;

    @ColumnInfo(name = "link")
    @Element(name = "link")
    public String link;

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "guid")
    @Element(name = "guid")
    public String guid;

    @ColumnInfo(name = "pubDate")
    @Element(name = "pubDate")
    public String pubDate;

    @ColumnInfo(name = "content")
    public String content = null;

    @ColumnInfo(name = "contentSnippet")
    public String contentSnippet = null;

    @ColumnInfo(name = "country")
    public String country = null;

    @ColumnInfo(name = "createdAt")
    public String createdAt = null;

    @ColumnInfo(name = "_id")
    public String id = null;

    @ColumnInfo(name = "type")
    public String type = null;

    @ColumnInfo(name = "updatedAt")
    public String updatedAt = null;

    @TypeConverters(DateConvertersJava.class)
    @ColumnInfo(name = "feedUpdatedAt")
    public Long feedUpdatedAt = null;

    @ColumnInfo(name = "__v")
    public int v = 0;

    @ColumnInfo(name = "mainType")
    public int mainType = 100;

    @ColumnInfo(name = "subType")
    public int subType = 100;

    @TypeConverters(DateConvertersJava.class)
    @ColumnInfo(name = "feedPubDate")
    public Long feedPubDate;

    @Embedded(prefix = "imgContent_")
    @Element(name = "content", required = false)
    public ImgContent imgContent = null;

    @ColumnInfo(name = "countryCode")
    public String countryCode = null;

}

