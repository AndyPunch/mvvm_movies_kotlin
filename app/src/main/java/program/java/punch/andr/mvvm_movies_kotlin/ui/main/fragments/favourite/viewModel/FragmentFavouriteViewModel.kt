package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.viewModel

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.ui.base.BaseViewModel
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.interfaces.FragmentFavouriteMonitor
import program.java.punch.andr.mvvm_movies_kotlin.utils.provider.ResourceProvider

class FragmentFavouriteViewModel(dataController: DataController, private val mResourceProvider: ResourceProvider) :
    BaseViewModel<FragmentFavouriteMonitor>(dataController) {

    var favouriteListViewModel: ObservableList<Movie> = ObservableArrayList()


    fun getFavourite() {
        getMyCompositeDisposable().add(
            getMyDataController().selectFavouriteMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    favouriteListViewModel.addAll(movies)
                    getMonitor()?.onMoviesLoaded(movies)
                }, {

                })
        )
    }

    fun deleteFavourite(movie: Movie) {
        getMyCompositeDisposable().add(
            getMyDataController().deleteFavourite(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    favouriteListViewModel.remove(movie)
                    getMonitor()?.onFavouriteDeleted(favouriteListViewModel)

                }, {

                })
        )
    }


}