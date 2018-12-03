package program.java.punch.andr.mvvm_movies_kotlin.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {


    var mActivity: BaseActivity<*, *>? = null
    private var mRootView: View? = null
    var mViewDataBinding: T? = null
    private var mViewModel: V? = null


    abstract fun getBindingVariable(): Int


    @get:LayoutRes
    abstract val layoutId: Int


    abstract fun getViewModel(): V

    fun isNetworkConnected(): Boolean = mActivity != null && mActivity!!.isNetworkConnected()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>
            this.mActivity = activity
            activity.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = mViewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

    fun hideKeyboard() {
        if (mActivity != null) {
            mActivity!!.hideSoftKeyboard(mActivity!!)
        }
    }

    fun getBaseActivity(): BaseActivity<*, *>? {
        return mActivity
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    fun getDataBinding(): T? {
        return mViewDataBinding
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}
