package program.java.punch.andr.mvvm_movies_kotlin.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import program.java.punch.andr.mvvm_movies_kotlin.data.local.db.favourite.FavouriteDao
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie


@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao

}
