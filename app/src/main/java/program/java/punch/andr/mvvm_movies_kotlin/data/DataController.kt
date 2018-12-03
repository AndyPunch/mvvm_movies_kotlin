package program.java.punch.andr.mvvm_movies_kotlin.data

import program.java.punch.andr.mvvm_movies_kotlin.data.local.db.DbHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.local.prefs.PreferencesHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.remote.ApiHelper

interface DataController : DbHelper, PreferencesHelper, ApiHelper {


}