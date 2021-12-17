package sk.sandeep.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sk.sandeep.newsapp.data.db.ArticleDAO
import sk.sandeep.newsapp.data.repository.data_source.NewsLocalDataSource
import sk.sandeep.newsapp.data.repository.data_source_impl.NewsLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDAO)
    }

}













