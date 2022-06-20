package com.vickikbt.library

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

class ComposeMarkdown constructor(private val context: Context) {

    private var markdownDocument: Document? = null
    private var connectionTimeout: Int = 5000

    fun setConnectionTimeout(mills: Int) {
        connectionTimeout = mills
    }

    fun loadFromString(markdown: String) {
        try {
            markdownDocument = Jsoup.parse(markdown)
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun loadFromFile(file: File) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                markdownDocument = Jsoup.parse(file)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun loadFromUrl(url: String) {
        if (!url.startsWith("https://") || !url.startsWith("http://")) {
            return
        } else {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    markdownDocument = Jsoup.connect(url).timeout(connectionTimeout).get()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }
        }

    }
}
