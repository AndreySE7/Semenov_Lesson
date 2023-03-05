package com.example.semenov_lesson.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.semenov_lesson.MainActivity
import com.example.semenov_lesson.router.Router.Companion.login
import com.example.semenov_lesson.router.Router.Companion.password
import com.example.semenov_lesson.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTextChangeListener()

        binding.buttonEnter.setOnClickListener {

            val enterLogin = binding.login.text.toString()
            val enterPassword = binding.password.text.toString()

            if (enterLogin == login && enterPassword == password) {
                binding.progressGroup.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.errorLogPass.visibility = View.VISIBLE
                binding.buttonEnter.isEnabled = false
            }
        }

        binding.buttonForgotPassword.setOnClickListener {
            binding.groupForgot.visibility = View.VISIBLE
        }

    }

    //разблокировка кнопки если пароль введен
    interface QueryTextListener {
        fun onQueryTextChange(newText: String?)
    }

    private var queryTextListener: QueryTextListener? = null

    private fun setTextChangeListener() {
        binding.password.doAfterTextChanged {
            binding.buttonEnter.isEnabled = !it.isNullOrBlank()
            queryTextListener?.onQueryTextChange(it.toString())
            binding.errorLogPass.visibility = View.GONE
        }
    }
}