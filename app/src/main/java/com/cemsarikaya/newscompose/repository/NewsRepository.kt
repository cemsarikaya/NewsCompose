package com.cemsarikaya.newscompose.repository

import com.cemsarikaya.newscompose.model.NewsList
import com.cemsarikaya.newscompose.service.NewsAPI
import com.cemsarikaya.newscompose.util.Constants.API_KEY
import com.cemsarikaya.newscompose.util.Constants.COUNTRY
import com.cemsarikaya.newscompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NewsRepository @Inject constructor(private val api : NewsAPI) {

    suspend fun getNewsList():Resource<NewsList>{

        val response = try {

            api.getNewsList(COUNTRY, API_KEY)

        }catch (e:Exception){

                return Resource.Error("ERROR")
        }
            return Resource.Success(response)
    }


}