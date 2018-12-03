package program.java.punch.andr.mvvm_movies_kotlin.utils.provider

import android.content.Context

class ResourceProvider(private val mContext: Context) {

    fun getString(resId: Int): String {
        return mContext.getString(resId)
    }

    fun getString(resId: Int, value: String): String {
        return mContext.getString(resId, value)
    }
}