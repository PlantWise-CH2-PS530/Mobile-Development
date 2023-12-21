package com.dicoding.plantwiseapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.dicoding.plantwiseapp.data.response.DataItem
import com.dicoding.plantwiseapp.databinding.ItemNewsRecentBinding

class NewsAdapterRecent : ListAdapter<DataItem, NewsAdapterRecent.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val binding = ItemNewsRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(user)
        }
    }

    class MyViewHolder(
        private val binding: ItemNewsRecentBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(user: DataItem){
            Glide.with(binding.root.context)
                .load(user.imageUrl)
                .into(binding.ivNewsItem)
            binding.tvTitleItem.text = user.tittle
            binding.tvDateItem.text = user.updatedAt
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
}