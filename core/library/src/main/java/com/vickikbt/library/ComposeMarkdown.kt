package com.vickikbt.library

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class ComposeMarkdown {

    suspend fun loadFromUrl(url: String) = withContext(Dispatchers.IO) {
        val document = Jsoup.connect(url).get()

        Log.e("TAG", "Document: $document")

        val imageUrl = document.select("#").firstOrNull()
        Log.e("TAG", "Image URL: $imageUrl")
    }
}
