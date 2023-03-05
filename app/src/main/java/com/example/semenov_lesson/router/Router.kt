package com.example.semenov_lesson.router

import androidx.fragment.app.Fragment
import com.example.semenov_lesson.MainActivity
import com.example.semenov_lesson.R
import com.example.semenov_lesson.fragment.HomeFragment
import com.example.semenov_lesson.fragment.OfficesFragment
import com.example.semenov_lesson.fragment.ThisOfficeFragment
import com.example.semenov_lesson.fragment.VacanciesFragment

class Router {

    companion object {
        const val KEY = "KEY"
        const val THIS_OFFICE = "THIS_OFFICE"
        const val OFFICES_SCREEN = "OFFICES_SCREEN"
        const val login = "admin"
        const val password = "55535"
    }

    var mainActivity: MainActivity? = null

    fun onCreate(activity: MainActivity) {
        mainActivity = activity
    }

    fun openHomeScreen() {
        openFragment(HomeFragment.newInstance(), false, KEY, null)
    }

    fun openVacanciesScreen() {
        openFragment(VacanciesFragment.newInstance(), true, null,null)
    }

    fun openOfficesScreen() {
        openFragment(OfficesFragment.newInstance(), true, null,OFFICES_SCREEN)

    }

    fun openThisOffice(officeName: String, officeAddress: String) {
        openFragment(ThisOfficeFragment.newInstance(officeName, officeAddress), true, THIS_OFFICE, null)
    }

    private fun openFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        key: String? = null,
        backStackName: String?
    ) {
        val transaction = mainActivity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentHome, fragment, key)
        if (addToBackStack) transaction?.addToBackStack(backStackName)
        transaction?.commit()
    }
}