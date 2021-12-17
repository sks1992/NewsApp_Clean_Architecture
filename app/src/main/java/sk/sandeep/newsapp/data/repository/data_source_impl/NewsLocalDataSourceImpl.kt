package sk.sandeep.newsapp.data.repository.data_source_impl

import kotlinx.coroutines.flow.Flow
import sk.sandeep.newsapp.data.db.ArticleDAO
import sk.sandeep.newsapp.data.model.Article
import sk.sandeep.newsapp.data.repository.data_source.NewsLocalDataSource

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticlesFromDB(article: Article) {
        articleDAO.deleteArticle(article)
    }
}