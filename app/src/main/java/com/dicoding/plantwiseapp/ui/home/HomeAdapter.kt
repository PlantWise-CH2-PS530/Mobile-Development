package com.dicoding.plantwiseapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.plantwiseapp.data.response.PlantResponse
import com.dicoding.plantwiseapp.databinding.ItemListPlantBinding

class HomeAdapter : ListAdapter<PlantResponse, HomeAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(plant)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(plant)
        }
    }

    class MyViewHolder(
        private val binding: ItemListPlantBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: PlantResponse) {
            binding.tvTitleItem.text = user.name
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlantResponse>() {
            override fun areItemsTheSame(oldItem: PlantResponse, newItem: PlantResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PlantResponse, newItem: PlantResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PlantResponse)
    }
}