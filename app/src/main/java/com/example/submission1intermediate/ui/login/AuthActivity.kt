package com.example.submission1intermediate.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1intermediate.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}