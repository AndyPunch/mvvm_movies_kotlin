package program.java.punch.andr.mvvm_movies_kotlin.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import program.java.punch.andr.mvvm_movies_kotlin.ApplicationMVVM
import program.java.punch.andr.mvvm_movies_kotlin.di.builder.ActivityBuilder
import program.java.punch.andr.mvvm_movies_kotlin.di.module.ApplicationModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [(AndroidInjectionModule::class), (ApplicationModule::class),
        (ActivityBuilder::class)]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: ApplicationMVVM)


}