package com.dicoding.plantwiseapp.data.response

import com.google.gson.annotations.SerializedName

data class PlantResponse(

	@field:SerializedName("soilMoisture")
	val soilMoisture: Int,

	@field:SerializedName("nitrogen")
	val nitrogen: Int,

	@field:SerializedName("about")
	val about: String,

	@field:SerializedName("recomFertilize1")
	val recomFertilize1: String,

	@field:SerializedName("kalium")
	val kalium: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("FertilizationRecommendations")
	val fertilizationRecommendations: String,

	@field:SerializedName("difficulty")
	val difficulty: String,

	@field:SerializedName("size")
	val size: String,

	@field:SerializedName("watering")
	val watering: String,

	@field:SerializedName("recomFertilize2")
	val recomFertilize2: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("recomFertilize3")
	val recomFertilize3: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("ph")
	val ph: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("fosfor")
	val fosfor: Int
)
