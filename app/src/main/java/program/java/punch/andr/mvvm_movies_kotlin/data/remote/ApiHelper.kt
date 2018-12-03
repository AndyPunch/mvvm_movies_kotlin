package program.java.punch.andr.mvvm_movies_kotlin.data.remote

import io.reactivex.Observable
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {
    @GET("/")
    fun getMovies(
        @Query("apikey") apikey: String,
        @Query("type") type: String,
        @Query("s") title: String
    ): Observable<Movies>
}
