package com.mohammadfayaz.ezetap.di

import com.google.gson.Gson
import com.mohammadfayaz.ezetap.BuildConfig
import com.mohammadfayaz.network.JsonUIService
import com.mohammadfayaz.network.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
  @Provides
  @Singleton
  fun provideOkHttpClient() = RetrofitConfig.buildOkHttpClient(BuildConfig.DEBUG)

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient) = RetrofitConfig.build(okHttpClient, Gson())

  @Provides
  @Singleton
  fun provideEzeTapService(retrofit: Retrofit): JsonUIService =
    retrofit.create(JsonUIService::class.java)
}
