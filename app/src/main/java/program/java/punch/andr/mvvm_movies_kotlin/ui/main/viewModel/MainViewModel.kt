package program.java.punch.andr.mvvm_movies_kotlin.ui.main.viewModel

import android.databinding.ObservableField
import android.text.TextUtils
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController
import program.java.punch.andr.mvvm_movies_kotlin.ui.base.BaseViewModel
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.interfaces.MainMonitor
import program.java.punch.andr.mvvm_movies_kotlin.utils.provider.ResourceProvider

class MainViewModel(dataController: DataController, private val mResourceProvider: ResourceProvider) :
    BaseViewModel<MainMonitor>(dataController) {

    var isSearchVisible: ObservableField<Boolean> = ObservableField(true)

    fun onSearchButtonClick() {
        getMonitor()?.startSearch()
    }

    fun isMovieTitleValid(movieTitle: String): Boolean {
        return !TextUtils.isEmpty(movieTitle)
    }


}
