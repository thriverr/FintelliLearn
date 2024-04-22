package com.example.myscreen

import retrofit2.http.GET

interface NewsApiService {
    @GET("everything?q=finance&from=2024-03-21&sortBy=publishedAt&apiKey=02c99cf44ea14d799d26ed81a26dd4a0")
    suspend fun getNews(): NewsResponse
}