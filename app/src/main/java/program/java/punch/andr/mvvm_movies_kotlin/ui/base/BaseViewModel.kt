package program.java.punch.andr.mvvm_movies_kotlin.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController

abstract class BaseViewModel<M>(val dataController: DataController) : ViewModel() {

    val compositeDisposable: CompositeDisposable
    var mMonitor: M? = null
    var isLoading = ObservableBoolean(false)

    init {
        this.compositeDisposable = CompositeDisposable()
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    fun getMonitor(): M? {
        return mMonitor
    }

    fun setMonitor(monitor: M) {
        this.mMonitor = monitor
    }

    fun getMyCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    fun getMyDataController(): DataController {
        return dataController
    }


}
