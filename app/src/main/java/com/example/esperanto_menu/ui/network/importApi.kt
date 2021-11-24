package com.example.esperanto_menu.ui.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://esperanto-tv.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RadioService {
    @GET("/radio2.json")
    suspend fun getRadio(): List<Udsendelse>
}

object ImportApi {
    val retrofitService: RadioService by lazy { retrofit.create(RadioService::class.java) }
}