package sk.sandeep.newsapp.data.repository.data_source

import kotlinx.coroutines.flow.Flow
import sk.sandeep.newsapp.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)

    fun getSavedArticles(): Flow<List<Article>>

    suspend fun deleteArticlesFromDB(article: Article)
}