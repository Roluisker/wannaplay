package com.game.core.extensions

import com.game.core.model.Category

fun Category.isValid(): Boolean =
    id != 0 && !title.isNullOrEmpty()

fun Category.equalTo(category: Category): Boolean {
    if (!this.isValid() || !category.isValid()) {
        return false
    }

    return this.id == category.id && this.title == category.title
}