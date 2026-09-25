package com.example.newsapp.data.model
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
): Parcelable

@Parcelize
data class Source(
    val id: String?,
    val name: String
) : Parcelable