package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.viewModel

import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import program.java.punch.andr.mvvm_movies_kotlin.R
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.ui.base.BaseViewModel
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.interfaces.FragmentMainMonitor
import program.java.punch.andr.mvvm_movies_kotlin.utils.AppConstants
import program.java.punch.andr.mvvm_movies_kotlin.utils.provider.ResourceProvider

class FragmentMainViewModel(dataController: DataController, private val mResourceProvider: ResourceProvider) :
    BaseViewModel<FragmentMainMonitor>(dataController) {

    var moviesListViewModel: List<Movie>? = null
    var status: ObservableField<String> = ObservableField(mResourceProvider.getString(R.string.empty_str))


    fun fetchMovies(title: String) {
        setIsLoading(true)
        getMyCompositeDisposable().add(
            getMyDataController().getMovies(AppConstants.API_KEY, AppConstants.TYPE, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    moviesListViewModel = response.movies
                    if (moviesListViewModel == null) {
                        setStatus(null)
                    }
                    getMonitor()?.onMoviesRequested(moviesListViewModel)
                    //
                    setIsLoading(false)
                }, { throwable ->
                    setStatus(null)
                    setIsLoading(false)

                })
        )

    }


    fun setStatus(title: String?) {
        when {
            title.equals(mResourceProvider.getString(R.string.empty_str)) -> status.set(mResourceProvider.getString(R.string.empty))
            title == null -> status.set(mResourceProvider.getString(R.string.no_results))
            else -> status.set(mResourceProvider.getString(R.string.empty_str))

        }
    }

    fun insertFavouriteMovie(movie: Movie) {
        getMyCompositeDisposable().add(
            getMyDataController().insertFavouriteMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ aBoolean ->
                    getMonitor()?.onMovieInserted(aBoolean)
                }, {
                    getMonitor()?.onMovieInserted(false)
                })
        )

    }
}