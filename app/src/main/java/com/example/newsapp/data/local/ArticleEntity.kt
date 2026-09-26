package com.example.newsapp.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Source

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val url: String,
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    @Embedded(prefix = "source_")
    val source: SourceEntity
)

data class SourceEntity(
    val id: String?,
    val name: String
)

fun ArticleEntity.toArticle(): Article {
    return Article(
        source = Source(id = source.id, name = source.name),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        url = url,
        author = author,
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        source = SourceEntity(id = source.id, name = source.name)
    )
}
