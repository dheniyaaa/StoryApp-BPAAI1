package com.example.submission1intermediate.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.submission1intermediate.MainActivity
import com.example.submission1intermediate.databinding.ActivitySplashScreenBinding
import com.example.submission1intermediate.ui.login.AuthActivity
import com.example.submission1intermediate.utils.ViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SplashScreen : AppCompatActivity(), KodeinAware {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var splashScreenViewModel: SplashScreenViewModel
    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreenViewModel = obtainViewModel(this@SplashScreen)

        userDirection()

    }

    private fun userDirection(){
        lifecycleScope.launchWhenResumed {
            launch {
                splashScreenViewModel.getAuthToken().collect{ token ->
                    if (token.isNullOrEmpty()){

                        Intent(this@SplashScreen, AuthActivity::class.java).also { intent ->
                            startActivity(intent)
                            finish()
                        }


                    } else {
                        Intent(this@SplashScreen, MainActivity::class.java).also { intent ->
                            intent.putExtra(MainActivity.EXTRA_TOKEN, token)
                            startActivity(intent)
                            finish()
                        }
                    }
                } }
        }
    }



    private fun obtainViewModel(activity: AppCompatActivity): SplashScreenViewModel{
        return ViewModelProvider(activity, viewModelFactory)[SplashScreenViewModel::class.java]
    }


}