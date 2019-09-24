package com.example.jasy.Model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



interface ApodService {
    @GET("planetary/apod")
    fun getApod(@Query("hd") hd: Boolean = false,
                @Query("date") date: String,
                @Query("api_key") apiKey: String): Call<ApodModel>

    @GET("planetary/apod")
    fun getApods(@Query("hd") hd: Boolean = false,
                @Query("start_date") startDate: String,
                 @Query("end_date") endDate: String,
                @Query("api_key") apiKey: String): Call<List<ApodModel>>

    companion object {
        var shared : ApodService = {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit =  Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApodService::class.java)
        }()
    }
}