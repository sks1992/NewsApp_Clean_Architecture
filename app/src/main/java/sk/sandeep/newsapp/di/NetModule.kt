package sk.sandeep.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sk.sandeep.newsapp.BuildConfig
import sk.sandeep.newsapp.data.api.NewsApiService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }
}