package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import program.java.punch.andr.mvvm_movies_kotlin.BR
import program.java.punch.andr.mvvm_movies_kotlin.R
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.databinding.FragmentMainBinding
import program.java.punch.andr.mvvm_movies_kotlin.ui.base.BaseFragment
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.MainActivity
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.adapters.MoviesAdapter
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.interfaces.FragmentMainMonitor
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.viewModel.FragmentMainViewModel
import program.java.punch.andr.mvvm_movies_kotlin.utils.AppConstants.MOVIE_TITLE
import program.java.punch.andr.mvvm_movies_kotlin.utils.extention.toast
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding, FragmentMainViewModel>(), FragmentMainMonitor {


    companion object {

        internal val TAG = MainFragment::class.java.simpleName

        fun newInstance(title: String?): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putString(MOVIE_TITLE, title)
            fragment.arguments = args
            return fragment
        }


    }

    var movieTitle: String? = null
    var fragmentBinding: FragmentMainBinding? = null

    lateinit var adapter: MoviesAdapter
    lateinit var mainActivity: MainActivity

    @Inject
    lateinit var fragmentViewModel: FragmentMainViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun getViewModel(): FragmentMainViewModel = fragmentViewModel


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
        setAdapter(fragmentBinding!!.recyclerview)
        setUp()


    }

    private fun setUp() {

        if (movieTitle != null && !movieTitle.equals(getString(R.string.empty_str))) {
            fragmentViewModel.fetchMovies(movieTitle!!)
        } else {
            fragmentViewModel.setStatus(movieTitle)
        }

    }

    private fun setAdapter(recyclerview: RecyclerView) {
        adapter = MoviesAdapter(fragmentViewModel)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )


    }

    private fun getExtra() {
        movieTitle = arguments!!.getString(MOVIE_TITLE)
    }


    override fun onMoviesRequested(moviesListViewModel: List<Movie>?) {
        if (moviesListViewModel != null && moviesListViewModel.isNotEmpty()) {
            adapter.addMoviesToAdapter(moviesListViewModel)
        }
    }

    override fun onMovieInserted(aBoolean: Boolean) {
        if (aBoolean) {
            getBaseActivity()?.toast(getString(R.string.movie_is_added))

        } else {
            getBaseActivity()?.toast(getString(R.string.already_added))
        }
    }


}
