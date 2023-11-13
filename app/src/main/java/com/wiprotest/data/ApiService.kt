package com.wiprotest.data

import com.wiprotest.domain.model.University
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun fetchUniversities(@Query("country") country: String): List<University>

    companion object {
        private var apiService: ApiService? = null

        fun getInstance(forLogin: Boolean? = false): ApiService {
                if (apiService == null) {
                    apiService = Retrofit.Builder()
                        .baseUrl("http://universities.hipolabs.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}