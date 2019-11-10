package com.game.core.model

data class ModuleInstallRequest(
    var moduleName: String,
    var modulePath: String,
    var currentStatus: InstallModuleStatus = InstallModuleStatus.LOADING_MODULE
) {
    enum class InstallModuleStatus {
        DOWNLOADING,
        INSTALLED,
        REQUIRES_USER_CONFIRMATION,
        ALREADY_INSTALLED,
        INSTALLING,
        FAILED,
        LOADING_MODULE
    }
}