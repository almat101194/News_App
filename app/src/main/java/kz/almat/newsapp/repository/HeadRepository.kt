package kz.almat.newsapp.repository

import kz.almat.newsapp.api.RetrofitInstance
import kz.almat.newsapp.db.ArticleDatabase
import kz.almat.newsapp.models.Article

class HeadRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int, pageSize: Int ,categoryTheme: String) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber, pageSize , categoryTheme)

    suspend fun getAllNews(allQuery: String, pageNumber: Int) =
        RetrofitInstance.api.getAllNews(allQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert9(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}