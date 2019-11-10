package com.game.bfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.game.bfinder.databinding.ActivityMainBinding
import com.game.core.AppConstants
import com.game.core.BaseActivity
import com.game.core.extensions.hide
import com.game.core.extensions.show
import com.google.android.play.core.splitinstall.*
import kotlinx.android.synthetic.main.activity_main.*

private const val PACKAGE_NAME = "com.group.pow"
private const val PACKAGE_NAME_ONDEMAND = "$PACKAGE_NAME.search"
private const val SEARCH_ACTIVITY_CLASSNAME = "$PACKAGE_NAME_ONDEMAND.SearchActivity"

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainActivityViewModel(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel.showInstallPanel.observe(binding.lifecycleOwner!!, Observer {
            binding.showInstallPanel = it
        })
    }

    fun loadAndLaunchModule(name: String) {
        viewModel.loadAndLaunchModule(name)
    }

    /*
    fun loadAndLaunchModule(name: String) {
        updateProgressMessage(getString(R.string.loading_module, name))

        if (manager.installedModules.contains(name)) {
            updateProgressMessage(getString(R.string.already_installed))
            onSuccessfulLoad(name, launch = true)
            return
        }

        val request = SplitInstallRequest.newBuilder()
            .addModule(name)
            .build()

        manager.startInstall(request)

        updateProgressMessage(getString(R.string.starting_install_for, name))
    }*/

    private fun updateProgressMessage(message: String) {
        if (installPanel.visibility == View.GONE) installPanel.show()
        currentTextProgress.text = message
    }

    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        progressBar.max = state.totalBytesToDownload().toInt()
        progressBar.progress = state.bytesDownloaded().toInt()

        updateProgressMessage(message)
    }

    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
        if (launch) {
            when (moduleName) {
                AppConstants.SEARCH_MODULE -> {
                    installPanel.hide()
                    launchActivity(SEARCH_ACTIVITY_CLASSNAME)
                }
            }
        }
    }

    override fun onResume() {
        viewModel.registerModuleInstallListener()
        super.onResume()
    }

    override fun onPause() {
        viewModel.removeModuleInstallListener()
        super.onPause()
    }

    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, className)
        startActivity(intent)
    }
}
