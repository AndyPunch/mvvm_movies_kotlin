package program.java.punch.andr.mvvm_movies_kotlin.ui.main.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import program.java.punch.andr.mvvm_movies_kotlin.ViewModelProviderFactory
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.viewModel.MainViewModel
import program.java.punch.andr.mvvm_movies_kotlin.utils.provider.ResourceProvider

@Module
class MainActivityModule {

    @Provides
    internal fun mainViewModelProvider(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

    @Provides
    internal fun provideMainViewModel(
        dataController: DataController,
        mResourceProvider: ResourceProvider
    ): MainViewModel {
        return MainViewModel(dataController, mResourceProvider)
    }
}
