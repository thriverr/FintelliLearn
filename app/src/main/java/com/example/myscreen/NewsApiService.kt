package com.example.myscreen

import retrofit2.http.GET

interface NewsApiService {
    @GET("https://newsapi.org/v2/everything?q=finance&from=2024-03-28&sortBy=publishedAt&apiKey=02c99cf44ea14d799d26ed81a26dd4a0")
    suspend fun getNews(): NewsResponse
}