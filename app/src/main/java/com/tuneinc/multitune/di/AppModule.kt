package com.tuneinc.multitune.di

import com.tuneinc.multitune.data.datasources.LocalMusicDataSource
import com.tuneinc.multitune.data.datasources.StreamingMusicDataSource
import com.tuneinc.multitune.data.repositories.MusicRepository
import com.tuneinc.multitune.data.repositories.MusicRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalMusicDataSource(): LocalMusicDataSource {
        return LocalMusicDataSource()
    }
    
    @Provides
    @Singleton
    fun provideStreamingMusicDataSource(): StreamingMusicDataSource {
        return StreamingMusicDataSource()
    }
    
    @Provides
    @Singleton
    fun provideMusicRepository(
        localMusicDataSource: LocalMusicDataSource,
        streamingMusicDataSource: StreamingMusicDataSource
    ): MusicRepository {
        return MusicRepositoryImpl(localMusicDataSource, streamingMusicDataSource)
    }
}