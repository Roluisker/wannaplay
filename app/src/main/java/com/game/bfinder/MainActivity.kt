package com.game.bfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.game.bfinder.databinding.ActivityMainBinding
import com.game.core.AppConstants
import com.game.core.BaseActivity
import com.game.core.extensions.hide
import com.game.core.extensions.show
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.android.synthetic.main.activity_main.*

private const val CONFIRMATION_REQUEST_CODE = 1
private const val PACKAGE_NAME = "com.group.pow"
private const val PACKAGE_NAME_ONDEMAND = "$PACKAGE_NAME.search"
private const val SEARCH_ACTIVITY_CLASSNAME = "$PACKAGE_NAME_ONDEMAND.SearchActivity"

class MainActivity : BaseActivity(), SplitInstallStateUpdatedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        manager = SplitInstallManagerFactory.create(this)
    }

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
    }

    override fun onStateUpdate(state: SplitInstallSessionState) {
        val multiInstall = state.moduleNames().size > 1
        val langsInstall = !state.languages().isEmpty()

        val names = if (langsInstall) {
            state.languages().first()
        } else state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                displayLoadingState(state, getString(R.string.downloading, names))
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                manager.startConfirmationDialogForResult(state, this, CONFIRMATION_REQUEST_CODE)
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
    }

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
        manager.registerListener(this)
        super.onResume()
    }

    override fun onPause() {
        manager.unregisterListener(this)
        super.onPause()
    }

    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, className)
        startActivity(intent)
    }
}
