package program.java.punch.andr.mvvm_movies_kotlin.ui.base

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import program.java.punch.andr.mvvm_movies_kotlin.utils.GeneralUtils
import program.java.punch.andr.mvvm_movies_kotlin.utils.NetworkUtils

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(), BaseFragment.Callback {


    var mViewDataBinding: T? = null
    var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    @get:LayoutRes
    abstract val layoutId: Int


    abstract fun getViewModel(): V


    abstract fun getBindingVariable(): Int


    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }


    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

    fun getDataBinding(): T? {
        return mViewDataBinding
    }

    fun hideSoftKeyboard(activity: Activity) {
        GeneralUtils.hideSoftKeyboard(activity)
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }


}
