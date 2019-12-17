package com.example.ktxexample.model

import com.example.ktxexample.local.dao.NewsFeedDao
import com.example.ktxexample.remote.ApiRSSService

class FeedRepository constructor(private var apiService: ApiRSSService, private var userDao: NewsFeedDao) {
}