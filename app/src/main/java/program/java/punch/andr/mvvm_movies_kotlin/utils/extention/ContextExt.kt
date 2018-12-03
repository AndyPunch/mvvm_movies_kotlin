package program.java.punch.andr.mvvm_movies_kotlin.utils.extention


import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.toastWithResource(id: Int) {

    val message = getString(id)

    return Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
