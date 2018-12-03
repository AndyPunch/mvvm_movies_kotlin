package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.di

import dagger.Module
import dagger.Provides
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.viewModel.FragmentMainViewModel
import program.java.punch.andr.mvvm_movies_kotlin.utils.provider.ResourceProvider


@Module
class MainFragmentModule {

    @Provides
    internal fun provideMainFragmentViewModel(
        dataController: DataController,
        mResourceProvider: ResourceProvider
    ): FragmentMainViewModel {
        return FragmentMainViewModel(dataController, mResourceProvider)
    }


}