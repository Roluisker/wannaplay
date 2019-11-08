package com.game.core.extensions

import android.content.Intent

fun Intent.addExtra(key: String, value: Any) {
    when (value) {
        is String -> addStringExtra(key, value)
        is Int -> addIntExtra(key, value)
    }
}

fun Intent.addStringExtra(key: String, value: Any) {
    putExtra(key, value as String)
}

fun Intent.addIntExtra(key: String, value: Any) {
    putExtra(key, value as Int)
}