package com.game.bfinder

import android.content.Context
import com.game.core.BaseViewModel
import com.google.android.play.core.splitinstall.*

private const val CONFIRMATION_REQUEST_CODE = 1

class MainActivityViewModel(val context: Context) : BaseViewModel(),
    SplitInstallStateUpdatedListener {

    var splitInstaller: SplitInstallManager = SplitInstallManagerFactory.create(context)

    enum class DataStatus {
        DOWNLOADING,
        INSTALLED,
        REQUIRES_USER_CONFIRMATION,
        ALREADY_INSTALLED,
        INSTALLING,
        FAILED,
        LOADING_MODULE
    }

    fun loadAndLaunchModule(name: String) {
        //updateProgressMessage(getString(R.string.loading_module, name))

        /*
        if (splitInstaller.installedModules.contains(name)) {
            updateProgressMessage(getString(R.string.already_installed))
            onSuccessfulLoad(name, launch = true)
            return
        }

        val request = SplitInstallRequest.newBuilder()
            .addModule(name)
            .build()

        splitInstaller.startInstall(request)

        updateProgressMessage(getString(R.string.starting_install_for, name))*/
    }

    override fun onStateUpdate(state: SplitInstallSessionState) {
        val multiInstall = state.moduleNames().size > 1
        val langsInstall = !state.languages().isEmpty()
/*
        val names = if (langsInstall) {
            state.languages().first()
        } else state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                displayLoadingState(state, getString(R.string.downloading, names))
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                splitInstaller.startConfirmationDialogForResult(
                    state,
                    context as MainActivity,
                    CONFIRMATION_REQUEST_CODE
                )
            }
            SplitInstallSessionStatus.INSTALLED -> {
                if (langsInstall) {
                    currentTextProgress.text = "INSTALL SUCK IN langsInstall"
                    //onSuccessfulLanguageLoad(names)
                } else {
                    onSuccessfulLoad(names, launch = !multiInstall)
                }
            }

            SplitInstallSessionStatus.INSTALLING -> displayLoadingState(
                state,
                getString(R.string.installing, names)
            )
            SplitInstallSessionStatus.FAILED -> {
                currentTextProgress.text = "INSTALL FAIL"
            }
        }
        */
    }

    fun registerModuleInstallListener() {
        splitInstaller.registerListener(this)
    }

    fun removeModuleInstallListener() {
        splitInstaller.unregisterListener(this)
    }
}