package com.cemsarikaya.newscompose.viewmodel
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cemsarikaya.newscompose.model.Article
import com.cemsarikaya.newscompose.repository.NewsRepository
import com.cemsarikaya.newscompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private  val repository: NewsRepository): ViewModel() {

    var newsList = mutableStateOf<List<Article>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    private val _isRefreshing = MutableStateFlow(false)


    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        loadNews()
    }



  fun loadNews(){

      viewModelScope.launch {

          isLoading.value = true

          val result = repository.getNewsList()
          when(result){

              //11.04.2022
              is Resource.Success ->{

                  if (result.data!=null){
                      val newsItem = result.data.articles.mapIndexed { index, article ->


                          Article(article.title,article.urlToImage,article.description,article.url)
                      }





                  errorMessage.value =" "
                  isLoading.value = false
                  newsList.value = newsItem
              }

              }
              is Resource.Error ->{

                  errorMessage.value = result.message!!
                  isLoading.value = false


              }


          }

      }


    }


    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {

            _isRefreshing.emit(true)
            delay(1000)
              loadNews()
            _isRefreshing.emit(false)

        }
    }


}
