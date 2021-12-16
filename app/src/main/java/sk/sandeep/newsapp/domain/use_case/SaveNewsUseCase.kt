package sk.sandeep.newsapp.domain.use_case

import sk.sandeep.newsapp.data.model.Article
import sk.sandeep.newsapp.domain.repository.NewsRepository

/**
Create use case for save News
add reference to newsRepository interface in constructor parameter
 * */
class SaveNewsUseCase(
    private val newsRepository: NewsRepository
) {
    //it does not return so can use single line function
    suspend fun execute(article: Article) =newsRepository.saveNews(article)
}