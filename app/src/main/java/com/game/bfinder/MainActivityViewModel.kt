package com.game.bfinder

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.game.core.BaseViewModel
import com.game.core.model.ModuleInstallRequest
import com.google.android.play.core.splitinstall.*
import com.game.core.model.ModuleInstallRequest.*

private const val CONFIRMATION_REQUEST_CODE = 1

class MainActivityViewModel(context: Context) : BaseViewModel(),
    SplitInstallStateUpdatedListener {

    val showInstallPanel: MutableLiveData<Boolean> = MutableLiveData()
    val launchModuleRequest: MutableLiveData<ModuleInstallRequest> = MutableLiveData()
    var splitInstaller: SplitInstallManager = SplitInstallManagerFactory.create(context)

    fun loadAndLaunchModule(moduleInstallRequest: ModuleInstallRequest) {
        isInstallPanelVisible(true)
        setCurrentLaunchRequest(moduleInstallRequest)

        if (splitInstaller.installedModules.contains(moduleInstallRequest.moduleName)) {
            currentInstallModuleStatus(InstallModuleStatus.ALREADY_INSTALLED)
            isInstallPanelVisible(false)
            return
        }

        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleInstallRequest.moduleName)
            .build()

        splitInstaller.startInstall(request)
    }

    private fun isInstallPanelVisible(visible: Boolean) {
        showInstallPanel.value = visible
    }

    private fun setCurrentLaunchRequest(lauchRequest: ModuleInstallRequest) {
        launchModuleRequest.value = lauchRequest
    }

    private fun currentInstallModuleStatus(status: InstallModuleStatus) {
        launchModuleRequest.value?.currentStatus = status
        launchModuleRequest.postValue(launchModuleRequest.value)
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