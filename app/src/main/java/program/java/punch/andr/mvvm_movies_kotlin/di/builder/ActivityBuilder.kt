package program.java.punch.andr.mvvm_movies_kotlin.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.MainActivity
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.di.MainActivityModule
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.di.FavouriteFragmentProvider
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.di.MainFragmentProvider


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(
        modules = [(MainActivityModule::class),
            (MainFragmentProvider::class),
            (FavouriteFragmentProvider::class)

        ]
    )
    abstract fun bindMainActivity(): MainActivity


}
