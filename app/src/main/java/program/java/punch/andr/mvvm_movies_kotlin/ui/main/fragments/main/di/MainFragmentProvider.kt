package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.MainFragment


@Module
internal abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    internal abstract fun provideMainFragmentFactory(): MainFragment
}