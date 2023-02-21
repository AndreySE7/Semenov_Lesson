package com.example.semenov_lesson

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.semenov_lesson.databinding.ActivityLinearLayoutBinding

class LinearLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLinearLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLinearLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gridLayoutButton.setOnClickListener {
            val intent = Intent(this@LinearLayoutActivity, GridLayoutActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.endButton.setOnClickListener {
            finish()
        }
    }
}