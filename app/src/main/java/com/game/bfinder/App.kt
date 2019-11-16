/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.bfinder

import com.google.android.play.core.splitcompat.SplitCompatApplication
import timber.log.Timber

class App : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        BuildConfig.DEBUG.takeIf { true }.apply { Timber.plant(Timber.DebugTree()) }
    }
}