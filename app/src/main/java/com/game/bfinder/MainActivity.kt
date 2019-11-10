package com.game.bfinder

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.game.bfinder.databinding.ActivityMainBinding
import com.game.core.AppConstants
import com.game.core.BaseActivity
import com.game.core.model.ModuleInstallRequest
import com.game.core.model.ModuleInstallRequest.*

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainActivityViewModel(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        init()
    }

    private fun init() {
        viewModel.showInstallPanel.observe(binding.lifecycleOwner!!, Observer {
            binding.showInstallPanel = it
        })
        viewModel.launchModuleRequest.observe(binding.lifecycleOwner!!, Observer {
            currentInstallModuleStatus(it)
        })
    }

    private fun currentInstallModuleStatus(currentRequest: ModuleInstallRequest) {
        when (currentRequest.currentStatus) {
            InstallModuleStatus.INSTALLING -> onInstalling(currentRequest)
            InstallModuleStatus.LOADING_MODULE -> updateProgressText(getString(R.string.loading_module))
            InstallModuleStatus.ALREADY_INSTALLED -> onAlreadyInstalled(currentRequest)
            InstallModuleStatus.DOWNLOADING -> updateProgressText(getString(R.string.downloading))
            InstallModuleStatus.INSTALLED -> onInstalled(currentRequest)
        }
    }

    private fun onInstalled(currentRequest: ModuleInstallRequest) {
        updateProgressText(getString(R.string.installed))
        onSuccessfulLoad(currentRequest.moduleName, currentRequest.modulePath)
    }

    private fun onInstalling(currentRequest: ModuleInstallRequest) {
        updateProgressText(getString(R.string.installing))
        binding.maxProgress = currentRequest.maxProgress
        binding.currentProgress = currentRequest.currentProgress
    }

    private fun onAlreadyInstalled(currentRequest: ModuleInstallRequest) {
        updateProgressText(getString(R.string.already_installed))
        onSuccessfulLoad(currentRequest.moduleName, currentRequest.modulePath)
    }

    private fun updateProgressText(text: String) {
        binding.currentTextProgress.text = text
    }

    private fun onSuccessfulLoad(moduleName: String, launchPath: String) {
        when (moduleName) {
            AppConstants.SEARCH_MODULE -> {
                launchActivity(launchPath)
            }
        }
    }

    fun loadAndLaunchModule(moduleInstallRequest: ModuleInstallRequest) {
        viewModel.loadAndLaunchModule(moduleInstallRequest)
    }

    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, className)
        startActivity(intent)
    }

    override fun onResume() {
        viewModel.registerModuleInstallListener()
        super.onResume()
    }

    override fun onPause() {
        viewModel.removeModuleInstallListener()
        super.onPause()
    }
}
