package program.java.punch.andr.mvvm_movies_kotlin.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import dagger.android.support.AndroidSupportInjection
import program.java.punch.andr.mvvm_movies_kotlin.utils.GeneralUtils
import program.java.punch.andr.mvvm_movies_kotlin.utils.NetworkUtils


abstract class BaseDialog : DialogFragment() {

    private var parentActivity: BaseActivity<*, *>? = null


    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun show(fragmentManager: FragmentManager, tag: String) {
        val transaction = fragmentManager.beginTransaction()

        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }


    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(this.context)
    }

    fun hideSoftKeyboard(activity: Activity) {
        GeneralUtils.hideSoftKeyboard(activity)
    }


    fun showSoftKeyboard(activity: Activity) {
        GeneralUtils.showSoftKeyboard(activity)
    }

    fun dismissDialog(tag: String) {
        dismiss()
        getBaseActivity()?.onFragmentDetached(tag)
    }

    private fun getBaseActivity(): BaseActivity<*, *>? {
        return parentActivity
    }

}