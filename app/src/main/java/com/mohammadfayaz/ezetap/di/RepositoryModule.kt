package com.mohammadfayaz.ezetap.di

import com.mohammadfayaz.ezetap.domain.JsonUIRepo
import com.mohammadfayaz.network.JsonUIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideJsonUiRepo(jsonUIService: JsonUIService) = JsonUIRepo(jsonUIService)
}