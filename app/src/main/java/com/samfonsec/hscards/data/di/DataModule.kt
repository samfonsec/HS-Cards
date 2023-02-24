package com.samfonsec.hscards.data.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samfonsec.hscards.data.api.CardApi
import com.samfonsec.hscards.data.api.InfoApi
import com.samfonsec.hscards.data.api.NetworkInterceptor
import com.samfonsec.hscards.data.dataSource.CardDataSource
import com.samfonsec.hscards.data.dataSource.InfoDataSource
import com.samfonsec.hscards.data.repository.CardDataRepository
import com.samfonsec.hscards.data.repository.InfoDataRepository
import com.samfonsec.hscards.domain.repository.CardRepository
import com.samfonsec.hscards.domain.repository.InfoRepository
import com.samfonsec.hscards.domain.utils.Constants.API_TIME_OUT
import com.samfonsec.hscards.domain.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

val networkModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .addInterceptor(NetworkInterceptor(context))
            .readTimeout(API_TIME_OUT, SECONDS)
            .connectTimeout(API_TIME_OUT, SECONDS)
            .build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient(androidContext()) }
    single { provideRetrofit(get(), get()) }
}

val infoModule = module {
    single<InfoApi> { get<Retrofit>().create(InfoApi::class.java) }
    single<InfoRepository> { InfoDataRepository(get()) }
    single { InfoDataSource(get()) }
}

val cardModule = module {
    single<CardApi> { get<Retrofit>().create(CardApi::class.java) }
    single<CardRepository> { CardDataRepository(get()) }
    single { CardDataSource(get()) }
}



