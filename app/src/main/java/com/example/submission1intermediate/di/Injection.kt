package com.example.submission1intermediate.di

import android.app.Application
import com.example.submission1intermediate.MainViewModel
import com.example.submission1intermediate.data.StoryRepository
import com.example.submission1intermediate.data.local.AuthPreference
import com.example.submission1intermediate.data.remote.RemoteDataSource
import com.example.submission1intermediate.ui.addstory.AddStoryViewModel
import com.example.submission1intermediate.ui.login.LoginViewModel
import com.example.submission1intermediate.ui.register.RegisterViewModel
import com.example.submission1intermediate.ui.splash.SplashScreenViewModel
import com.example.submission1intermediate.utils.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Injection: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@Injection))

        bind() from this.provider { RemoteDataSource() }

        bind() from this.provider { applicationContext }

        bind<StoryRepository>() with this.provider {
            StoryRepository(this.instance(), this.instance()) }

        bind() from this.provider { AuthPreference(this.instance()) }


        bind() from this.provider {AddStoryViewModel(this.instance())}

        //bind() from this.provider {DetailStoryViewModel(this.instance())}

        bind() from this.provider {LoginViewModel(this.instance())}

        bind() from this.provider {RegisterViewModel(this.instance())}

        bind() from this.provider { SplashScreenViewModel(this.instance()) }

        bind() from this.provider { MainViewModel(this.instance()) }

        bind() from this.singleton { ViewModelFactory(this.instance()) }
    }
}