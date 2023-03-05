package com.example.semenov_lesson.router.viewModel

import androidx.lifecycle.ViewModel
import com.example.semenov_lesson.MainActivity
import com.example.semenov_lesson.dto.OfficesPost
import com.example.semenov_lesson.router.Router

class MainViewModel : ViewModel() {

    private var router: Router? = null

    init {
        router = Router()
    }

    fun onRouter(mainActivity: MainActivity) {
        router?.onCreate(mainActivity)
    }

    fun openHomeScreen() {
        router?.openHomeScreen()
    }

    fun openVacanciesScreen() {
        router?.openVacanciesScreen()
    }

    fun openOfficesScreen() {
        router?.openOfficesScreen()
    }

    fun openDetailsScreen(officesPost: OfficesPost) {
        router?.openThisOffice(officesPost.officesName, officesPost.officesAddress)
    }
}