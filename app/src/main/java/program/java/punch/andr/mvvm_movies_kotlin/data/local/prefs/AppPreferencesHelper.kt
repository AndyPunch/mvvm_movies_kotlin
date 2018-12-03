package program.java.punch.andr.mvvm_movies_kotlin.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import program.java.punch.andr.mvvm_movies_kotlin.di.PreferenceInfo
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
    context: Context,
    @PreferenceInfo private val
    prefFileName: String
) : PreferencesHelper {


    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)


}