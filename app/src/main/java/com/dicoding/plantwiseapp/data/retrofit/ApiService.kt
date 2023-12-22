package com.dicoding.plantwiseapp.data.retrofit

import com.dicoding.plantwiseapp.data.response.GithubResponse
import com.dicoding.plantwiseapp.data.response.ItemsItem
import com.dicoding.plantwiseapp.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<GithubResponse>

    @GET("news/{NewsId}")
    fun getDetailNewsById(@Path("id") id : String): Call<NewsResponse>

    @GET("hottest-news")
    fun getHottestNews(): Call<NewsResponse>

    @GET("latest-news")
    fun getRecentNews(): Call<NewsResponse>
}
