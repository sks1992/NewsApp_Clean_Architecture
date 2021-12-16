package sk.sandeep.newsapp.data.repository.data_source

import retrofit2.Response
import sk.sandeep.newsapp.data.model.ApiResponse


/**
 * inside this  interface we will define abstract function to communicate with api
 * */
interface NewsRemoteDataSource {

    //we are going to define function to communicate newsApiService function getTopHeadlines
    suspend fun getTopHeadlines(country: String, page :Int):Response<ApiResponse>

}