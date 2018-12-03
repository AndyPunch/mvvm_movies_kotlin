package program.java.punch.andr.mvvm_movies_kotlin.data.local.db

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie

import javax.inject.Inject

class AppDbHelper @Inject
constructor(private val mAppDatabase: AppDatabase) : DbHelper {

    override fun insertFavouriteMovie(movie: Movie): Single<Boolean> = Single.fromCallable {
        mAppDatabase.favouriteDao().insertFavouriteMovie(movie)

        true
    }

    override fun selectFavouriteMovies(): Observable<List<Movie>> {
        return Observable.fromCallable { mAppDatabase.favouriteDao().selectFavouriteMovies() }

    }

    override fun deleteFavourite(movie: Movie): Completable {
        return Completable.fromAction { mAppDatabase.favouriteDao().deleteFavouriteMovies(movie) }
    }


}