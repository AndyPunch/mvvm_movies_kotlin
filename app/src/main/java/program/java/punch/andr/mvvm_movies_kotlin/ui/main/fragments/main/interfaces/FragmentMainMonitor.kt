package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.interfaces

import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie

interface FragmentMainMonitor {
    fun onMoviesRequested(moviesListViewModel: List<Movie>?)

    fun onMovieInserted(aBoolean: Boolean)

}