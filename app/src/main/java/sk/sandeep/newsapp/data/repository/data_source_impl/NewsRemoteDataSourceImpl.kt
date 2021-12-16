package sk.sandeep.newsapp.data.repository.data_source_impl

import retrofit2.Response
import sk.sandeep.newsapp.data.api.NewsApiService
import sk.sandeep.newsapp.data.model.ApiResponse
import sk.sandeep.newsapp.data.repository.data_source.NewsRemoteDataSource


class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<ApiResponse> {
        return newsApiService.getTopHeadlines(country, page)
    }
}