package com.dicoding.plantwiseapp.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.plantwiseapp.data.response.DataItem
import com.dicoding.plantwiseapp.data.response.GithubResponse
import com.dicoding.plantwiseapp.data.response.ItemsItem
import com.dicoding.plantwiseapp.data.response.NewsResponse
import com.dicoding.plantwiseapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

//    private val _news = MutableLiveData<List<DataItem>>()
//    val news: LiveData<List<DataItem>> = _news

    private val _hottestNews = MutableLiveData<List<DataItem>>()
    val hottestNews: LiveData<List<DataItem>> = _hottestNews

    private val _recentNews = MutableLiveData<List<DataItem>>()
    val recentNews: LiveData<List<DataItem>> = _recentNews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun findHottest() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getHottestNews()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _hottestNews.value = responseBody.data
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun findRecent() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getRecentNews()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _recentNews.value = responseBody.data
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

//     fun findHottest() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getHottestNews()
//        client.enqueue(object : Callback<NewsResponse> {
//            override fun onResponse(
//                call: Call<NewsResponse>,
//                response: Response<NewsResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        _news.value = responseBody.data
//                        Log.d("Berhasil", response.body().toString())
//                    }
//                } else {
//                    _isLoading.value = false
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
//
//     fun findRecent() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getRecentNews()
//        client.enqueue(object : Callback<NewsResponse> {
//            override fun onResponse(
//                call: Call<NewsResponse>,
//                response: Response<NewsResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        _news.value = responseBody.data
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }

    companion object {
        private const val TAG = "MainActivity"
    }
}