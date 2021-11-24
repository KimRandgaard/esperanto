package com.example.esperanto_menu.ui.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://esperanto-radio.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//interface importServiceNomo {
//    @GET("nomo")
//    suspend fun getNomo(): List<import>
//    @GET("plennomo")
//    suspend fun getPlennomo(): List<import>
//    @GET("hejmo")
//    suspend fun getHejmo(): List<import>
//    @GET("mp3fajlo")
//    suspend fun getMp3fajlo(): List<import>
//    @GET("dato")
//    suspend fun getDato(): List<import>
//    @GET("teksto")
//    suspend fun getTeksto(): List<import>
//}
interface RadioService {
    @GET("/radio.json")
    suspend fun getRadio(): import
}

object ImportApi {
    val retrofitService: RadioService by lazy { retrofit.create(RadioService::class.java) }
}