package sk.sandeep.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import sk.sandeep.newsapp.data.model.ApiResponse
import sk.sandeep.newsapp.data.model.Article
import sk.sandeep.newsapp.data.repository.data_source.NewsRemoteDataSource
import sk.sandeep.newsapp.data.util.Resource
import sk.sandeep.newsapp.domain.repository.NewsRepository

/**
 * create a class that implement newsRepository methods
 * */
class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    //create a function to input the response instance of type NewsApiResponse
    //returned from the api and a output a resource instance of type newsApiResponse
    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<ApiResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

}