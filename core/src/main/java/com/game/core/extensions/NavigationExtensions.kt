package com.game.core.extensions

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.openModule(url: String) {

    Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    ).apply {
        addCategory(Intent.CATEGORY_BROWSABLE)
    }.let {
        startActivity(it)
    }

}