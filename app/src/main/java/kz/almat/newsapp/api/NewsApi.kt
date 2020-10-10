package kz.almat.newsapp.api

import kz.almat.newsapp.models.NewsResponse
import kz.almat.newsapp.util.Constans.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",

        @Query("page")
        pageNumber: Int = 15,

        @Query("pageSize")
        pageSize: Int = 15,

        @Query("category")
        categoryTheme: String = "science",

        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q")
        allQuery: String,

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}