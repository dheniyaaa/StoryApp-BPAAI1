package com.example.submission1intermediate.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission1intermediate.MainViewModel
import com.example.submission1intermediate.data.StoryRepository
import com.example.submission1intermediate.ui.addstory.AddStoryViewModel
import com.example.submission1intermediate.ui.login.LoginViewModel
import com.example.submission1intermediate.ui.register.RegisterViewModel
import com.example.submission1intermediate.ui.splash.SplashScreenViewModel

class ViewModelFactory(private val repository: StoryRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{

            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
                AddStoryViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }

            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> {
                SplashScreenViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class" + modelClass.name)
        }
    }
}