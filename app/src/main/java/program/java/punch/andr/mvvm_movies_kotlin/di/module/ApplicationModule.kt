package program.java.punch.andr.mvvm_movies_kotlin.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import program.java.punch.andr.mvvm_movies_kotlin.data.AppDataController
import program.java.punch.andr.mvvm_movies_kotlin.data.DataController
import program.java.punch.andr.mvvm_movies_kotlin.data.local.db.AppDatabase
import program.java.punch.andr.mvvm_movies_kotlin.data.local.db.AppDbHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.local.db.DbHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.local.prefs.AppPreferencesHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.local.prefs.PreferencesHelper
import program.java.punch.andr.mvvm_movies_kotlin.data.remote.ApiHelper
import program.java.punch.andr.mvvm_movies_kotlin.di.PreferenceInfo
import program.java.punch.andr.mvvm_movies_kotlin.utils.AppConstants
import program.java.punch.andr.mvvm_movies_kotlin.utils.provider.ResourceProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, AppConstants.DB_NAME
    ).build()

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }


    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    internal fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    internal fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    internal fun provideApiHelper(retrofit: Retrofit): ApiHelper {
        return retrofit.create<ApiHelper>(ApiHelper::class.java)
    }

    @Provides
    @Singleton
    internal fun provideResourceProvider(mContext: Context): ResourceProvider {
        return ResourceProvider(mContext)
    }

    @Provides
    @Singleton
    internal fun provideDataController(appDataController: AppDataController): DataController {
        return appDataController
    }

    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    internal fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }
}