/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.core.extensions

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.openModule(url: String, extras: HashMap<String, *>? = null) {
    Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    ).apply {
        addCategory(Intent.CATEGORY_BROWSABLE)
        extras?.forEach { extra ->
            addExtra(extra.key, extra.value)
        }
    }.let {
        startActivity(it)
    }
}

