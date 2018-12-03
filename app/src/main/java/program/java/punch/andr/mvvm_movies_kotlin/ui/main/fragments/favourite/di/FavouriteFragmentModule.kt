package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.di

import dagger.Module
import dagger.Provides
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.viewModel.FragmentFavouriteViewModel
import program.java.punch.andr.mvvm_movies_kotlin.utils.provider.ResourceProvider


@Module
class FavouriteFragmentModule {

    @Provides
    internal fun provideFavouriteFragmentViewModel(
        dataController: DataController,
        mResourceProvider: ResourceProvider
    ): FragmentFavouriteViewModel {
        return FragmentFavouriteViewModel(dataController, mResourceProvider)
    }


}