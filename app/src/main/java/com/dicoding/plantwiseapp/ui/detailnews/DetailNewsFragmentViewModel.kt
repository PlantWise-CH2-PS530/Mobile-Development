package com.dicoding.plantwiseapp.ui.detailnews

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

class DetailNewsFragmentViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _detailNews = MutableLiveData<DataItem>()
    val detailNews: LiveData<DataItem> = _detailNews

    private fun getNewsDetail(id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailNewsById(id)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailNews.value = responseBody.data[0]
                        Log.d("Berhasil", response.body().toString())
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

    fun searchNewsDetail(followers: String) {
        getNewsDetail(followers)
    }

    companion object {
        private const val TAG = "DetailFragment"
    }
}