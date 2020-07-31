package com.tiki.tikitesting.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tiki.tikitesting.data.services.HomeService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object API {
    val gson: Gson = GsonBuilder()
        .disableHtmlEscaping()
        .create()
    val TIME_OUT = 10L

    /**
     * Any API request will go here
     * In this case, any request will have a header called sample-header
     * For different client, please make different clientInterceptor
     */
    private val clientInterceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .build()
        return@Interceptor chain.proceed(request)
    }

    /**
     * Use for normal request
     */
    var client: Retrofit = makeClient("https://api.tiki.vn/")


    private fun makeHttpClient(interceptor: Interceptor?): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        interceptor?.let { httpClient.addInterceptor(it) }
        return httpClient.build()
    }


    fun makeClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(makeHttpClient(clientInterceptor))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    /**
     * Service class goes here, Repository will access Service class function
     */
    val homeService = client.create(HomeService::class.java) as HomeService

}
