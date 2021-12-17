package sk.sandeep.newsapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sk.sandeep.newsapp.BuildConfig
import sk.sandeep.newsapp.data.model.ApiResponse

/**
 * In NewsApiService we define function to get data from the news api with url end point
 * and query parameters
 * */
interface NewsApiService {
    //function to get topHeadlines from the news api with url
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<ApiResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ): Response<ApiResponse>
}