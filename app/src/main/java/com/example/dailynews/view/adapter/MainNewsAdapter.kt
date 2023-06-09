package com.example.dailynews.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailynews.data.News
import com.example.dailynews.databinding.ItemNewBinding

class MainNewsAdapter : RecyclerView.Adapter<MainNewsAdapter.MainViewHolder>() {

    private var newsData: List<News> = listOf()
    private var onItemClickListener: (News) -> Unit = {}
    private var onShareClickListener: (News) -> Unit = {}
    private var onSaveClickListener: (News) -> Unit = {}

    fun setOnItemClickListener(onItemClickListener: (News) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnShareClickListener(onShareClickListener: (News) -> Unit) {
        this.onShareClickListener = onShareClickListener
    }

    fun setOnSaveClickListener(onSaveClickListener: (News) -> Unit) {
        this.onSaveClickListener = onSaveClickListener
    }

    fun setData(data: List<News>) {
        newsData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(newsData[position])
    }

    override fun getItemCount(): Int {
        return newsData.size
    }

    inner class MainViewHolder(val binding: ItemNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.apply {
                tvTitle.text = news.title
                tvSourceName.text = news.source.name
                Glide
                    .with(root)
                    .load(news.urlToImage)
                    .into(ivNewsPic)
                root.setOnClickListener {
                    onItemClickListener(news)
                }
                ivShare.setOnClickListener {
                    onShareClickListener(news)
                }
                ivSave.setOnClickListener {
                    onSaveClickListener(news)
                }
            }
        }
    }
}