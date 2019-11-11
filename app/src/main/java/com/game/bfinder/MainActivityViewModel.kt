package com.game.bfinder

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.game.core.BaseViewModel
import com.game.core.model.ModuleInstallRequest
import com.google.android.play.core.splitinstall.*
import com.game.core.model.ModuleInstallRequest.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

private const val CONFIRMATION_REQUEST_CODE = 1

class MainActivityViewModel(private val context: Context) : BaseViewModel(),
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

    override fun onStateUpdate(state: SplitInstallSessionState) {
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                currentInstallModuleStatus(InstallModuleStatus.DOWNLOADING)
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                splitInstaller.startConfirmationDialogForResult(
                    state,
                    context as MainActivity,
                    CONFIRMATION_REQUEST_CODE
                )
            }
            SplitInstallSessionStatus.INSTALLED -> {
                currentInstallModuleStatus(InstallModuleStatus.INSTALLED)
            }
            SplitInstallSessionStatus.INSTALLING -> {
                currentInstallModuleStatus(InstallModuleStatus.INSTALLING)
                currentDownloadingState(state)
            }
            SplitInstallSessionStatus.FAILED -> {
                currentInstallModuleStatus(InstallModuleStatus.FAILED)
            }
            SplitInstallSessionStatus.CANCELED -> {

            }
            SplitInstallSessionStatus.CANCELING -> {

            }
            SplitInstallSessionStatus.DOWNLOADED -> {

            }
            SplitInstallSessionStatus.PENDING -> {

            }
            SplitInstallSessionStatus.UNKNOWN -> {

            }
        }
    }

    private fun currentInstallModuleStatus(status: InstallModuleStatus) {
        launchModuleRequest.value?.currentStatus = status
        launchModuleRequest.postValue(launchModuleRequest?.value)
    }

    private fun currentDownloadingState(state: SplitInstallSessionState) {
        launchModuleRequest.value?.maxProgress = state.totalBytesToDownload().toInt()
        launchModuleRequest.value?.currentProgress = state.bytesDownloaded().toInt()
        launchModuleRequest.postValue(launchModuleRequest?.value)
    }

    private fun isInstallPanelVisible(visible: Boolean) {
        showInstallPanel.value = visible
    }

    private fun setCurrentLaunchRequest(lauchRequest: ModuleInstallRequest) {
        launchModuleRequest.value = lauchRequest
    }

    fun registerModuleInstallListener() {
        splitInstaller.registerListener(this)
    }

    fun removeModuleInstallListener() {
        splitInstaller.unregisterListener(this)
    }
}