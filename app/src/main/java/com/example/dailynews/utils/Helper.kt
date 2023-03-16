package com.example.dailynews.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.dailynews.base.BaseApp
import com.example.dailynews.data.News
import com.example.dailynews.data.Source
import com.example.dailynews.db.NewsEntity

object Helper {

    fun toastShort(text: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(BaseApp.appInstance, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun toastLong(text: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(BaseApp.appInstance, text, Toast.LENGTH_LONG).show()
        }
    }

    fun hideKeyboard(activity: Activity) {
        try {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val view: View? = if (activity.currentFocus == null) {
                View(activity)
            } else {
                activity.currentFocus
            }
            imm.hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (ignored: Throwable) {
        }
    }

    fun hideKeyboard(fragment: Fragment) {
        val activity: Activity? = fragment.activity
        activity?.let { hideKeyboard(it) }
    }

    fun copyToClipBoard(url: String){
        val clipboard = BaseApp.appInstance?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("",url)
        clipboard.setPrimaryClip(clip)
        Helper.toastShort("Url copy to clip board")
    }

    fun convertEntitiesToModel(entites: List<NewsEntity>): List<News> {
        val newsList: MutableList<News> = mutableListOf()
        entites.forEach {
            newsList.add(
                News(
                    source = Source(id = null, name = it.source),
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    url = it.url,
                    urlToImage = it.urlToImage.toString(),
                    publishedAt = it.publishedAt,
                    content = it.content,
                )
            )
        }
        return newsList
    }
}