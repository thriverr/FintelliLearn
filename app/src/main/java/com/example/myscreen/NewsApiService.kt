package com.example.myscreen

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything?sortBy=publishedAt&apiKey=02c99cf44ea14d799d26ed81a26dd4a0")
    suspend fun getNews(@Query("q") query: String = "finance"): NewsResponse

    @GET("everything?sortBy=publishedAt&apiKey=02c99cf44ea14d799d26ed81a26dd4a0")
    suspend fun searchNews(@Query("q") query: String = ""): NewsResponse
}