package sk.sandeep.newsapp.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import sk.sandeep.newsapp.data.model.Article

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}