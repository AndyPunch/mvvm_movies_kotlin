package program.java.punch.andr.mvvm_movies_kotlin.data

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import program.java.punch.andr.mvvm_movies_kotlin.data.local.db.DbHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.local.prefs.PreferencesHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movies
import program.java.punch.andr.mvvm_movies_kotlin.data.remote.ApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataController @Inject
constructor(
    private val mContext: Context,
    private val mDbHelper: DbHelper,
    private val mPreferencesHelper: PreferencesHelper,
    private val mApiHelper: ApiHelper

) : DataController {

    override fun insertFavouriteMovie(movie: Movie): Single<Boolean> {
        return mDbHelper.insertFavouriteMovie(movie)
    }

    override fun getMovies(apikey: String, type: String, title: String): Observable<Movies> {
        return mApiHelper.getMovies(apikey, type, title)
    }

    override fun selectFavouriteMovies(): Observable<List<Movie>> {
        return mDbHelper.selectFavouriteMovies()
    }

    override fun deleteFavourite(movie: Movie): Completable {
        return mDbHelper.deleteFavourite(movie)
    }


}
