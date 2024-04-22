package com.example.myscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class NewsViewModel : ViewModel() {
    private val apiService: NewsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(NewsApiService::class.java)
    }

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> get() = _news

    init {
        // Fetch news articles when ViewModel is initialized
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = apiService.getNews()
                _news.value = response.articles
            } catch (e: Exception) {
                // Log or handle the error appropriately
                e.printStackTrace()
                // Optionally, update UI state to show error
            }
        }
    }
}
