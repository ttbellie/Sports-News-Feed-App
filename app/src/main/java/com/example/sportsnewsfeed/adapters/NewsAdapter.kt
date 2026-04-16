package com.example.sportsnewsfeed.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeed.R
import com.example.sportsnewsfeed.data.NewsItem

class NewsAdapter(
    private var items: List<NewsItem>,
    private val onItemClick: (NewsItem) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.iv_news_image)
        val title: TextView = view.findViewById(R.id.tv_news_title)
        val category: TextView = view.findViewById(R.id.tv_news_category)
        val description: TextView = view.findViewById(R.id.tv_news_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.imageResId)
        holder.title.text = item.title
        holder.category.text = item.category
        holder.description.text = item.description
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<NewsItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
