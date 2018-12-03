package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.interfaces

import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie

interface FragmentFavouriteMonitor {
    fun onMoviesLoaded(moviesList: List<Movie>)
    fun onFavouriteDeleted(moviesList: List<Movie>)
}