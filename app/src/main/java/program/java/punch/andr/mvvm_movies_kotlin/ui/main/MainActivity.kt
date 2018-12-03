package program.java.punch.andr.mvvm_movies_kotlin.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import program.java.punch.andr.mvvm_movies_kotlin.BR
import program.java.punch.andr.mvvm_movies_kotlin.R
import program.java.punch.andr.mvvm_movies_kotlin.databinding.ActivityMainBinding
import program.java.punch.andr.mvvm_movies_kotlin.ui.base.BaseActivity
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.FavouriteFragment
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.MainFragment
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.interfaces.MainMonitor
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.viewModel.MainViewModel
import program.java.punch.andr.mvvm_movies_kotlin.utils.extention.addFragment
import program.java.punch.andr.mvvm_movies_kotlin.utils.extention.replaceFragment
import program.java.punch.andr.mvvm_movies_kotlin.utils.extention.setupActionBar
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainMonitor, HasSupportFragmentInjector {

    @Inject
    lateinit var mMainViewModel: MainViewModel

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var isFavouriteVisible = true

    var mActivityMainBinding: ActivityMainBinding? = null

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel::class.java)
        return mMainViewModel
    }


    override fun getBindingVariable(): Int = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = getDataBinding()
        mMainViewModel.setMonitor(this)
        setSupportActionBar(mActivityMainBinding?.toolbar)
        setTitle(R.string.app_name)
        addFragment(MainFragment.newInstance(getString(R.string.empty_str)), R.id.container)

    }


    override fun startSearch() {
        hideSoftKeyboard(this)
        val title = mActivityMainBinding!!.searchEdittext.text.toString().trim()
        if (isNetworkConnected()) {
            if (mMainViewModel.isMovieTitleValid(title)) {
                setMovies(title)
            } else {
                Toast.makeText(this, R.string.title_is_empty, Toast.LENGTH_LONG).show()

            }
        } else {
            Toast.makeText(this, R.string.network_not_aviable, Toast.LENGTH_LONG).show()
        }

    }

    fun setMovies(title: String) {
        replaceFragment(MainFragment.newInstance(title), R.id.container)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_favourite, menu)
        val favouriteItem = menu.findItem(R.id.action_favourie)

        favouriteItem.isVisible = isFavouriteVisible
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_favourie) {
            hideSoftKeyboard(this)
            replaceFragment(FavouriteFragment.newInstance(), R.id.container, FavouriteFragment.TAG)
            isFavouriteVisible = false
            invalidateOptionsMenu()
        } else if (id == android.R.id.home) {
            onBackPressed()
            isFavouriteVisible = true
            invalidateOptionsMenu()
        }

        return super.onOptionsItemSelected(item)
    }


    fun setViewSettings(tag: String) {
        when (tag) {
            MainFragment.TAG -> {
                mMainViewModel.isSearchVisible.set(true)
                setupActionBar(R.id.toolbar) {
                    title = getString(R.string.app_name)
                    setDisplayHomeAsUpEnabled(false)
                    setDisplayShowHomeEnabled(false)

                }

            }

            FavouriteFragment.TAG -> {
                mMainViewModel.isSearchVisible.set(false)
                setupActionBar(R.id.toolbar) {
                    title = getString(R.string.favourite)
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)

                }
            }
        }

    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector


}
