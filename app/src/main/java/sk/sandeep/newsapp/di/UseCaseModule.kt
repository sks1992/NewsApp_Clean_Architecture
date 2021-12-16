package sk.sandeep.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sk.sandeep.newsapp.domain.repository.NewsRepository
import sk.sandeep.newsapp.domain.use_case.GetNewsHeadlinesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetNewsHeadlinesUseCaseModule(newsRepository: NewsRepository): GetNewsHeadlinesUseCase {
        return GetNewsHeadlinesUseCase(newsRepository)
    }
}

