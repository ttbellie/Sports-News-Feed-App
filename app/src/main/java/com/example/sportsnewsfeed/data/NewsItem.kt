package com.example.sportsnewsfeed.data

data class NewsItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageResId: Int,
    val category: String,
    val isFeatured: Boolean = false
)
