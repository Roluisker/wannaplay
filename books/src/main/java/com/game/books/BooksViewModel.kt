package com.game.books

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.game.bfinder.MainActivity
import com.game.books.repository.BooksRepository
import com.game.core.AppConstants
import com.game.core.BaseViewModel
import com.game.core.model.Book
import com.game.core.model.ModuleInstallRequest
import com.google.android.play.core.splitinstall.*
import com.game.core.model.ModuleInstallRequest.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

private const val CONFIRMATION_REQUEST_CODE = 1
private const val PACKAGE_NAME = "com.group.pow"
private const val PACKAGE_NAME_ONDEMAND = "$PACKAGE_NAME.search"
private const val SEARCH_ACTIVITY_CLASSNAME = "$PACKAGE_NAME_ONDEMAND.SearchActivity"

class BooksViewModel(booksRepository: BooksRepository, private val context: Context) : BaseViewModel(),
    SplitInstallStateUpdatedListener{

    private var splitInstaller: SplitInstallManager = SplitInstallManagerFactory.create(context)
    private val _categoryId: MutableLiveData<Int> = MutableLiveData()

    val showInstallPanel: MutableLiveData<Boolean> = MutableLiveData()
    val launchModuleRequest: MutableLiveData<ModuleInstallRequest> = MutableLiveData()

    val books: LiveData<ArrayList<Book>> = Transformations
        .switchMap(_categoryId) { id ->
            booksRepository.fetchBooks(id)
        }

    fun onClickBookSearch(view: View) {
        loadAndLaunchModule(ModuleInstallRequest(AppConstants.SEARCH_MODULE, SEARCH_ACTIVITY_CLASSNAME))
    }

    fun searchByCategoryId(categoryId: Int) {
        _categoryId.value = categoryId
    }

    private fun loadAndLaunchModule(moduleInstallRequest: ModuleInstallRequest) {
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