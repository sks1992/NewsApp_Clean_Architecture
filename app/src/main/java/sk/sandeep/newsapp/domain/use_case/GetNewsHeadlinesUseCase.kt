package sk.sandeep.newsapp.domain.use_case

import sk.sandeep.newsapp.data.model.ApiResponse
import sk.sandeep.newsapp.data.util.Resource
import sk.sandeep.newsapp.domain.repository.NewsRepository


/**
Create use case for get NewsHeadlines
add reference to newsRepository interface in constructor parameter
 * */
class GetNewsHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(country: String, page: Int): Resource<ApiResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}

//use case store reference of repository