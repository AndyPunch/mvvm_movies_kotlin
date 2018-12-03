package program.java.punch.andr.mvvm_movies_kotlin.data.local.db

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie


interface DbHelper {
    fun insertFavouriteMovie(movie: Movie): Single<Boolean>
    fun selectFavouriteMovies(): Observable<List<Movie>>
    fun deleteFavourite(movie: Movie): Completable
}