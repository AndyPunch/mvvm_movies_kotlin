package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.databinding.ItemMovieBinding
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.viewModel.FragmentMainViewModel
import program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.main.viewModel.ItemMovieViewModel


class MoviesAdapter(var fragmentViewModel: FragmentMainViewModel) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    var moviesList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(itemMovieBinding)
    }


    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bindMovie(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }


    inner class MoviesHolder(internal var mItemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(mItemMovieBinding.itemMovie) {

        internal fun bindMovie(movie: Movie) {
            val itemMovieViewModel = ItemMovieViewModel(movie)
            mItemMovieBinding.viewModel = itemMovieViewModel
            mItemMovieBinding.executePendingBindings()

            itemMovieViewModel!!.favouriteClick.subscribe {
                fragmentViewModel.insertFavouriteMovie(movie)
            }

        }
    }

    fun addMoviesToAdapter(list: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(list)
        notifyDataSetChanged()
    }

}
