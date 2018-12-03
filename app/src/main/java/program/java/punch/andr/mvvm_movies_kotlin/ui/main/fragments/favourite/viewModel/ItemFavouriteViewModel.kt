package program.java.punch.andr.mvvm_movies_kotlin.ui.main.fragments.favourite.viewModel

import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.view.View
import android.widget.ImageView
import com.jakewharton.rxrelay2.PublishRelay
import program.java.punch.andr.mvvm_movies_kotlin.R
import program.java.punch.andr.mvvm_movies_kotlin.data.model.db.Movie
import program.java.punch.andr.mvvm_movies_kotlin.utils.glide.GlideApp


@BindingAdapter("imageUrl")
fun setImgUrl(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context).load(url).placeholder(R.drawable.noimage).into(imageView)

}

class ItemFavouriteViewModel(private val movie: Movie) {
    val title: ObservableField<String?> = ObservableField(movie.title)
    var poster: ObservableField<String?> = ObservableField(movie.poster)

    val deleteClick = PublishRelay.create<Movie>()


    fun onDeleteIconClick(view: View) {
        deleteClick.accept(movie)
    }
}
