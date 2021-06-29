package com.example.wtest.utils

import androidx.appcompat.widget.SearchView
import java.text.Normalizer

val String.Companion.SPACE: String
    get() = " "

val String.Companion.EMPTY: String
    get() = ""

inline fun SearchView.onQueryTextSubmit(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            if (!query.isNullOrBlank()) {
                val trimQuery = query.stripAccents().getUnmasked().trim()
                listener(trimQuery)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    })
}

fun String.stripAccents(): String {
    var s = this
    s = Normalizer.normalize(s, Normalizer.Form.NFD)
    s = s.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    return s
}

fun String?.getUnmasked(): String {
    this?.apply {
        return this.replace(".", String.SPACE)
            .replace(".", String.EMPTY)
            .replace("/", String.EMPTY)
            .replace("(", String.EMPTY)
            .replace(")", String.EMPTY)
            .replace(" ", String.SPACE)
            .replace("-", String.SPACE)
            .replace(",", String.SPACE)
    }
    return String.SPACE
}