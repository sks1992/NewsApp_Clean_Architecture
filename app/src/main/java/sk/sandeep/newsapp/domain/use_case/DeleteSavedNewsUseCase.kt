package sk.sandeep.newsapp.domain.use_case

import sk.sandeep.newsapp.data.model.Article
import sk.sandeep.newsapp.domain.repository.NewsRepository


/**
Create use case foe delete saved news
add reference to newsRepository interface in constructor parameter
 * */
class DeleteSavedNewsUseCase(
    private val newsRepository: NewsRepository
) {
    //it does not return so can use single line function
    suspend fun execute(article: Article) =newsRepository.deleteNews(article)
}