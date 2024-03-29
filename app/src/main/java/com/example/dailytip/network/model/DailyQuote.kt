package com.example.dailytip.network.model

import com.google.gson.annotations.SerializedName

data class DailyQuote(
    @SerializedName("author")
    val author: String,
    @SerializedName("authorSlug")
    val authorSlug: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("dateAdded")
    val dateAdded: String,
    @SerializedName("dateModified")
    val dateModified: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("tags")
    val tags: List<String>
)