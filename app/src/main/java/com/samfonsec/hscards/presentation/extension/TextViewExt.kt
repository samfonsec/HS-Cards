package com.samfonsec.hscards.presentation.extension

import android.text.Html
import android.widget.TextView


fun TextView.setTextFromHtml(html: String) {
    text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).trim()
}
