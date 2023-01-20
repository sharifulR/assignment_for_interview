package com.example.assignmentapp.network

import com.example.assignmentapp.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkClient {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthService(retrofit: Retrofit) : AuthApi{
        return retrofit.create(AuthApi::class.java)
    }
    @Singleton
    @Provides
    fun providesProductService(retrofit: Retrofit) : ProductApi{
        return retrofit.create(ProductApi::class.java)
    }

    var gson = GsonBuilder()
        .setLenient()
        .create()

    var timeOut = 5 * 60
    private var client: OkHttpClient = OkHttpClient.Builder()//Builder()
        .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        .build()

}