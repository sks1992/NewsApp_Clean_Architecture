package sk.sandeep.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import sk.sandeep.newsapp.data.model.ApiResponse
import sk.sandeep.newsapp.data.model.Article
import sk.sandeep.newsapp.data.repository.data_source.NewsLocalDataSource
import sk.sandeep.newsapp.data.repository.data_source.NewsRemoteDataSource
import sk.sandeep.newsapp.data.util.Resource
import sk.sandeep.newsapp.domain.repository.NewsRepository

/**
 * create a class that implement newsRepository methods
 * */
class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<ApiResponse> {
        return responseToResource(
            newsRemoteDataSource.getSearchedNews(country, searchQuery, page)
        )
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


    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        return newsLocalDataSource.deleteArticlesFromDB(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles()
    }

}