package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import program.java.punch.andr.mvvm_movies_kotlin.BR
import program.java.punch.andr.mvvm_movies_kotlin.R
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.databinding.FragmentFavouriteBinding
import program.java.punch.andr.mvvm_movies_kotlin.ui.base.BaseFragment
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.MainActivity
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.adapters.FavouriteAdapter
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.interfaces.FragmentFavouriteMonitor
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.viewModel.FragmentFavouriteViewModel
import program.java.punch.andr.mvvm_movies_kotlin.utils.extention.toast
import javax.inject.Inject

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding, FragmentFavouriteViewModel>(),
    FragmentFavouriteMonitor {


    companion object {

        internal val TAG = FavouriteFragment::class.java.simpleName

        fun newInstance(): FavouriteFragment {
            val fragment = FavouriteFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }


    }


    var fragmentBinding: FragmentFavouriteBinding? = null
    lateinit var mainActivity: MainActivity

    lateinit var adapter: FavouriteAdapter

    @Inject
    lateinit var fragmentViewModel: FragmentFavouriteViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_favourite


    override fun getViewModel(): FragmentFavouriteViewModel = fragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        getExtra()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        mainActivity.setViewSettings(TAG)
        fragmentBinding = getDataBinding()
        fragmentViewModel.setMonitor(this)
        setAdapter(fragmentBinding!!.recyclerviewFavourite)
        setUp()

    }

    private fun setUp() {

    }

    private fun setAdapter(recyclerview: RecyclerView) {
        adapter = FavouriteAdapter(fragmentViewModel)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )

        if (fragmentViewModel.favouriteListViewModel.isNotEmpty()) {
            adapter.addFavouriteToAdapter(fragmentViewModel.favouriteListViewModel)
        } else {
            fragmentViewModel.getFavourite()
        }
    }


    override fun onMoviesLoaded(moviesList: List<Movie>) {
        adapter.addFavouriteToAdapter(moviesList)
    }

    override fun onFavouriteDeleted(moviesList: List<Movie>) {
        adapter.addFavouriteToAdapter(moviesList)
        getBaseActivity()?.toast(getString(R.string.favourite_deleted))
    }


    private fun getExtra() {

    }


}
