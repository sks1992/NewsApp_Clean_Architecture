package sk.sandeep.newsapp.domain.use_case

import kotlinx.coroutines.flow.Flow
import sk.sandeep.newsapp.data.model.Article
import sk.sandeep.newsapp.domain.repository.NewsRepository

/**
Create use case to view saved news
add reference to newsRepository interface in constructor parameter
 * */
class GetSavedNewsUseCase(
    private val newsRepository: NewsRepository
) {
    fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}