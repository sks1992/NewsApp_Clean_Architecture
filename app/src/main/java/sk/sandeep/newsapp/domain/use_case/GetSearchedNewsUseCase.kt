package sk.sandeep.newsapp.domain.use_case

import sk.sandeep.newsapp.data.model.ApiResponse
import sk.sandeep.newsapp.data.util.Resource
import sk.sandeep.newsapp.domain.repository.NewsRepository

/**
Create use case for search news
add reference to newsRepository interface in constructor parameter
 * */
class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<ApiResponse> {
        return newsRepository.getSearchedNews(country, searchQuery, page)
    }
}