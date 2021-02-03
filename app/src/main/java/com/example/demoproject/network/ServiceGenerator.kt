package com.example.demoproject.network

import com.example.demoproject.utils.BASE_URL
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ServiceGenerator {

    companion object {

        fun getClient(): RetroInterface {
            return Retrofit.Builder()
                    .client(createOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(getGsonClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(RetroInterface::class.java)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(Interceptor {
                val requestNew = it.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
                return@Interceptor it.proceed(requestNew)
            })

            return httpClient.build()
        }

        fun getGsonClient(): GsonConverterFactory{
            var gson= GsonBuilder().setLenient().create()
            var gClient = GsonConverterFactory.create(gson)
            return gClient;

        }
    }
}