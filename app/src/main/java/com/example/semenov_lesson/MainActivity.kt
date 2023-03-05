package com.example.semenov_lesson

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.semenov_lesson.databinding.ActivityMainBinding
import com.example.semenov_lesson.dto.OfficesPost
import com.example.semenov_lesson.router.Router
import com.example.semenov_lesson.router.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.onRouter(this)

        startHomeScreen()
        setBottomNavigation()
        onBackMainOrCloseApp()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homePage -> {
                    openHomeBottomNavigation()
                    true
                }
                R.id.vacancies -> {
                    openVacanciesBottomNavigation()
                    true
                }
                R.id.offices -> {
                    openOfficesBottomNavigation()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun startHomeScreen() {
        viewModel.openHomeScreen()
    }

    private fun openHomeBottomNavigation() {
        if (binding.bottomNavigation.selectedItemId == R.id.homePage) return
        nameActionBar(getString(R.string.toolbar_home), false)
        viewModel.openHomeScreen()
    }

    private fun openVacanciesBottomNavigation() {
        if (binding.bottomNavigation.selectedItemId == R.id.vacancies) return
        nameActionBar(getString(R.string.toolbar_vacancies), false)
        viewModel.openVacanciesScreen()
    }

    private fun openOfficesBottomNavigation() {
        if (binding.bottomNavigation.selectedItemId == R.id.offices) return
        nameActionBar(getString(R.string.toolbar_offices), false)

        viewModel.openOfficesScreen()
    }

    fun openOffice(officesPost: OfficesPost) {
        viewModel.openDetailsScreen(officesPost)
        nameActionBar(officesPost.officesName, true)

    }

    private fun nameActionBar(title: String, backButtonVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(backButtonVisible)
        supportActionBar?.title = title
    }

    private fun onBackMainOrCloseApp() {
        onBackPressedDispatcher.addCallback(this) {
            when {
                binding.bottomNavigation.selectedItemId == R.id.homePage -> moveTaskToBack(true)
                isOnThisOffice() -> onBackThisOffice()
                else -> binding.bottomNavigation.selectedItemId = R.id.homePage
            }
        }
    }

    private fun isOnThisOffice(): Boolean {
        return binding.bottomNavigation.selectedItemId == R.id.offices
                && supportFragmentManager.findFragmentByTag(Router.THIS_OFFICE) != null
    }

    private fun onBackThisOffice() {
        supportFragmentManager.popBackStack()
        nameActionBar(getString(R.string.offices), false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}