package kz.almat.newsapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.almat.newsapp.models.Article
import kz.almat.newsapp.models.NewsResponse
import kz.almat.newsapp.repository.HeadRepository
import kz.almat.newsapp.util.Resource
import retrofit2.Response

class HeadViewModel(
    val headRepository: HeadRepository
): ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null

    val allNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var allNewsPage = 1
    var allNewsResponse: NewsResponse? = null

    init {
        getBreakingNews("us", "science")
    }

    fun getBreakingNews(countryCode: String, categoryTheme: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = headRepository.getBreakingNews(countryCode,  breakingNewsPage, categoryTheme)
        breakingNews.postValue(handleBreakingNewsResponse(response))
//        delay(5000L)
    }

    fun getAllNews(allQuery: String) = viewModelScope.launch {
        allNews.postValue(Resource.Loading())
        val response = headRepository.getAllNews(allQuery , allNewsPage)
        allNews.postValue(handleAllNewsResponse(response))
//        delay(5000L)
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let {resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleAllNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let {resultResponse ->
                allNewsPage++
                if(allNewsResponse == null) {
                    allNewsResponse = resultResponse
                } else {
                    val oldArticles = allNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(allNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        headRepository.upsert(article)
    }

    fun getSavedNews() = headRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        headRepository.deleteArticle(article)
    }
}