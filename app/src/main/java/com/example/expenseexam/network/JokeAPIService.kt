package com.example.expenseexam.network

import com.example.expenseexam.data.Joke
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JokeAPIService {
    @GET("random_joke")
    suspend fun getJoke(): Joke
}

private const val BASE_URL = "https://official-joke-api.appspot.com/"

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val jokeAPIService: JokeAPIService = retrofit.create(JokeAPIService::class.java)