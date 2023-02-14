package com.example.randomuser.data.repositories.api

import com.example.randomuser.data.model.Result
import com.example.randomuser.data.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author Soorianarayanan
 */
interface UserService {
    @GET("/api/")
    fun getUsers(@Query("results") result: Int): Call<User>

    companion object{
        private const val BASE_URL = "https://randomuser.me/"

        fun create(): UserService {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(UserService::class.java)
        }
    }
}
