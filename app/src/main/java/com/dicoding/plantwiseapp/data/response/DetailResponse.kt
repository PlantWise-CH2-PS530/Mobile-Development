package com.dicoding.plantwiseapp.data.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("insertedAt")
	val insertedAt: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("tag")
	val tag: List<String>,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("tittle")
	val tittle: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
