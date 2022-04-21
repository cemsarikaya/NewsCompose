package com.cemsarikaya.newscompose.service

import com.cemsarikaya.newscompose.model.NewsList
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

        @GET("top-headlines")
        suspend fun getNewsList(

            @Query("country") country: String,
            @Query("apiKey") apiKey : String


        ) :  NewsList //List<Article>



}