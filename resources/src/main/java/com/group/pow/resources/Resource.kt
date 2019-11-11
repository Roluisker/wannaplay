package com.group.pow.resources

import com.group.pow.resources.Status.*

data class Resource<out T>(val status: Status, val data: T?, val message: String) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, "")
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }
    }
}