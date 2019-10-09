package com.game.firebase.livedata

import android.os.Handler
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData

private const val STOP_LISTENING_DELAY = 2000L

abstract class LingeringLiveData<T> : LiveData<T>() {

    private val handler = Handler()

    private var stopLingeringPending = false
    private val stopLingeringRunnable = StopLingeringRunnable()

    abstract fun beginLingering()

    abstract fun endLingering()

    @CallSuper
    override fun onActive() {
        if (stopLingeringPending) {
            handler.removeCallbacks(stopLingeringRunnable)
        } else {
            beginLingering()
        }
        stopLingeringPending = false
    }

    @CallSuper
    override fun onInactive() {
        handler.postDelayed(
            stopLingeringRunnable,
            STOP_LISTENING_DELAY
        )
        stopLingeringPending = true
    }

    private inner class StopLingeringRunnable : Runnable {
        override fun run() {
            if (stopLingeringPending) {
                stopLingeringPending = false
                endLingering()
            }
        }
    }

}