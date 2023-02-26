package com.example.semenov_lesson

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.semenov_lesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contactsButton.setOnClickListener {
            binding.progressGroup.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.vacanciesButton.setOnClickListener {
            binding.progressGroup.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.galleryButton.setOnClickListener {
            binding.progressGroup.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

        }
    }
}
