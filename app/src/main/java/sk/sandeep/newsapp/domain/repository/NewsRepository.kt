package sk.sandeep.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import sk.sandeep.newsapp.data.model.ApiResponse
import sk.sandeep.newsapp.data.model.Article
import sk.sandeep.newsapp.data.util.Resource

/**
 * In NewsRepository we define abstract function to communicate with newsApiService
 * we use newsResponseApi in Resource class so we can use loading,success,error state
 * */

interface NewsRepository {
    //to get topNewsHeadlines
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse>

    //to get search result for searchQuery
    suspend fun getSearchedNews(searchQuery: String): Resource<ApiResponse>

    //save news in local dataBase
    suspend fun saveNews(article: Article)

    //delete saved news for local dataBase
    suspend fun deleteNews(article: Article)

    //get list of saved news
    //we want to get realtime update for saved news and we want to get notified every change that
    //happen in the Article table so we use Flow api.we can use live data but it is lifecycle aware
    //and it can create unexpected error so we use flow that is recommended by google
    fun getSavedNews(): Flow<List<Article>>
}

//in repository we define abstract function
//by using flow we will get the list of data from the data base as a flow.in view model  class
// we will collect this stream of dat flow and emit it as live data.Since this function return
// a data stream we don't need to write this function  as a suspended function