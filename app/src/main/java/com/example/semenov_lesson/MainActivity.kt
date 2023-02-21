package com.example.semenov_lesson

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.semenov_lesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linearLayoutButton.setOnClickListener {
            val intent = Intent(this@MainActivity, LinearLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}