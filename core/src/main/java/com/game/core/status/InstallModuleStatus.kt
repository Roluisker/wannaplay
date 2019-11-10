package com.game.core.status

enum class InstallModuleStatus {
    DOWNLOADING,
    INSTALLED,
    REQUIRES_USER_CONFIRMATION,
    ALREADY_INSTALLED,
    INSTALLING,
    FAILED,
    LOADING_MODULE
}