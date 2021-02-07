package com.arabam.android_assignment.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arabam.android_assignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        val toolbar: ToolBarBinding = binding.toolbar1
        supportActionBar?.setDisplayShowTitleEnabled(false)


    }


}