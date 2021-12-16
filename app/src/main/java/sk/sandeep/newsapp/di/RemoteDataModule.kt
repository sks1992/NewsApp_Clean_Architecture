package sk.sandeep.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sk.sandeep.newsapp.data.api.NewsApiService
import sk.sandeep.newsapp.data.repository.data_source.NewsRemoteDataSource
import sk.sandeep.newsapp.data.repository.data_source_impl.NewsRemoteDataSourceImpl
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsApiService
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}