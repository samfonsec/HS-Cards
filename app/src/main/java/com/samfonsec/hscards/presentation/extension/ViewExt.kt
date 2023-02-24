package com.samfonsec.hscards.presentation.extension

import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible


fun View.show() {
    isVisible = true
}

fun View.hide() {
    isVisible = false
}

fun TextView.setTextFromHtml(html: String) {
    text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).trim()
}
