package program.java.punch.andr.mvvm_movies_kotlin.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun isNetworkConnected(context: Context?): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
