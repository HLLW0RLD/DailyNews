package com.example.dailynews.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dailynews.base.BaseApp

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
}