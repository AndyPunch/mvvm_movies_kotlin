package program.java.punch.andr.mvvm_movies_kotlin.data.local.db.favourite

import android.arch.persistence.room.*
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFavouriteMovie(movie: Movie)

    @Query("SELECT * FROM favourite_movies")
    fun selectFavouriteMovies(): List<Movie>

    @Delete
    fun deleteFavouriteMovies(movie: Movie)
}