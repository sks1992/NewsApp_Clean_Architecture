package sk.sandeep.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sk.sandeep.newsapp.data.repository.NewsRepositoryImpl
import sk.sandeep.newsapp.data.repository.data_source.NewsRemoteDataSource
import sk.sandeep.newsapp.domain.repository.NewsRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}