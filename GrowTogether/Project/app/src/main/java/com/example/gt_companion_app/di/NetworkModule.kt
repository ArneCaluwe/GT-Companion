package com.example.gt_companion_app.di



import com.example.gt_companion_app.BuildConfig
import com.example.gt_companion_app.data.AppDatabase
import com.example.gt_companion_app.data.local.bouts.BoutLocalDataSource
import com.example.gt_companion_app.data.local.scores.AthleteScoreLocalDataSource
import com.example.gt_companion_app.data.local.sets.SetLocalDataSource
import com.example.gt_companion_app.data.remote.BoutRemoteDataSource
import com.example.gt_companion_app.data.remote.GT_ApiService
import com.example.gt_companion_app.data.remote.SetRemoteDataSource
import com.example.gt_companion_app.repos.AthleteScoreRepository
import com.example.gt_companion_app.repos.BoutRepository
import com.example.gt_companion_app.repos.SetRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single {
        provideApiService(get())
    }
    single { AppDatabase.getDatabase(androidApplication()).setDao() }
    single { AppDatabase.getDatabase(androidApplication()).boutDao() }
    single { AppDatabase.getDatabase(androidApplication()).athleteScoreDao() }
    single { AppDatabase.getDatabase(androidApplication()).athleteDao() }
    single { BoutRemoteDataSource(get()) }
    single { BoutLocalDataSource(get(), get(), get(), get()) }
    single { BoutRepository(get(), get()) }
    single { SetRemoteDataSource(get()) }
    single { SetLocalDataSource(get(), get()) }
    single { SetRepository(get(), get()) }
    single { AthleteScoreLocalDataSource(get())}
    single { AthleteScoreRepository(get()) }
}

/**
 * Provided a OkHttpClient. In debug version, an interceptor is added
 * as to log the network information which has been received.
 */
private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

/**
 * Provide the retrofit instance
 */
private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build()
            )
        )
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

/**
 * Provide the API service
 */
private fun provideApiService(retrofit: Retrofit): GT_ApiService =
    retrofit.create(GT_ApiService::class.java)