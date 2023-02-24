package com.example.semenov_lesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.semenov_lesson.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time = Time(0, 0, 0)
        thread {
            while (true) {
                val currTime = Calendar.getInstance()
                val hour = currTime.get(Calendar.HOUR)
                val minutes = currTime.get(Calendar.MINUTE)
                val seconds = currTime.get(Calendar.SECOND)
                time.setTime(hour, minutes, seconds)
                runOnUiThread {
                    binding.watchView.setNewTime(time)
                }
                Thread.sleep(1000)
            }
        }

    }
}