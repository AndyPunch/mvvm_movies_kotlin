package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.databinding.ItemFavouriteBinding
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.viewModel.FragmentFavouriteViewModel
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.viewModel.ItemFavouriteViewModel


class FavouriteAdapter(var fragmentViewModel: FragmentFavouriteViewModel) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder>() {

    var favouriteList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteHolder {
        val itemFavouriteBinding = ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteHolder(itemFavouriteBinding)
    }


    override fun onBindViewHolder(holder: FavouriteHolder, position: Int) {
        holder.bindFavourite(favouriteList[position])
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }


    inner class FavouriteHolder(internal var mItemFavouriteBinding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(mItemFavouriteBinding.itemFavourite) {

        internal fun bindFavourite(movie: Movie) {
            val itemFavouriteViewModel = ItemFavouriteViewModel(movie)
            mItemFavouriteBinding.viewModel = itemFavouriteViewModel
            mItemFavouriteBinding.executePendingBindings()

            itemFavouriteViewModel.deleteClick.subscribe {
                fragmentViewModel.deleteFavourite(movie)
            }

        }

    }

    fun addFavouriteToAdapter(list: List<Movie>) {
        favouriteList.clear()
        favouriteList.addAll(list)
        notifyDataSetChanged()
    }

}
