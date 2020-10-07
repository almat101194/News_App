package kz.almat.newsapp.repository

import kz.almat.newsapp.api.RetrofitInstance
import kz.almat.newsapp.db.ArticleDatabase

class HeadRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int, categoryTheme: String) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber, categoryTheme)

    suspend fun getAllNews(allQuery: String, pageNumber: Int) =
        RetrofitInstance.api.getAllNews(allQuery, pageNumber)
}