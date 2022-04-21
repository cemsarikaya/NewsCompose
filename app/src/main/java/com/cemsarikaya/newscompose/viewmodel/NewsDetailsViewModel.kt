package com.cemsarikaya.newscompose.viewmodel

import androidx.lifecycle.ViewModel
import com.cemsarikaya.newscompose.model.NewsList
import com.cemsarikaya.newscompose.repository.NewsRepository
import com.cemsarikaya.newscompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {


 suspend fun getNews() : Resource<NewsList> {


     return repository.getNewsList()



 }

}


