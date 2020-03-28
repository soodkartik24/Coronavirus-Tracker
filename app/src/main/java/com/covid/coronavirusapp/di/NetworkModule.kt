package com.covid.coronavirusapp.di

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.covid.coronavirusapp.BuildConfig
import com.covid.coronavirusapp.network.ApiClient
import com.covid.coronavirusapp.util.Constants

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {

        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            //.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))

        if (BuildConfig.DEBUG)
            httpBuilder.addInterceptor(loggingInterceptor)
        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()

    }

    @Provides
    @Singleton
    fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", Constants.API_KEY)

            val actualRequest = request.build()
            chain.proceed(actualRequest)
        }
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
    }


    @Provides
    @Singleton
    fun getApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }


}