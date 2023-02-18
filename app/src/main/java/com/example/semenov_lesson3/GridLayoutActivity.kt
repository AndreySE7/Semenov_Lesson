package com.example.semenov_lesson3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.semenov_lesson3.databinding.ActivityGridLayoutBinding

class GridLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGridLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.endButton.setOnClickListener {
            val intent = Intent(this@GridLayoutActivity, LinearLayoutActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.homeButton.setOnClickListener {
            finish()
        }
    }
}