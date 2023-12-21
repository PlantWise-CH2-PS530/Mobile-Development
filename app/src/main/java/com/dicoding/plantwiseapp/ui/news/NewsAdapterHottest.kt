package com.dicoding.plantwiseapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.plantwiseapp.data.response.DataItem
import com.dicoding.plantwiseapp.databinding.ItemNewsHottestBinding

class NewsAdapterHottest: ListAdapter<DataItem, NewsAdapterHottest.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsHottestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(news)
        }
    }

    class MyViewHolder(private val binding: ItemNewsHottestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            Glide.with(binding.root.context)
                .load(user.imageUrl)
                .into(binding.ivNewsItem2)
            binding.tvTitleItem2.text = user.tittle
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