package com.example.dailynews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailynews.R
import com.example.dailynews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.apply {
                beginTransaction()
                    .add(R.id.container, MainNewsFragment.newInstance())
                    .commitAllowingStateLoss()
            }
        }
    }
}