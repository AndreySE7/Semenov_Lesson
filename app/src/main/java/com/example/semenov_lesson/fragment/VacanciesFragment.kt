package com.example.semenov_lesson.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.semenov_lesson.R
import com.example.semenov_lesson.adapter.VacanciesAdapter
import com.example.semenov_lesson.databinding.FragmentVacanciesBinding
import com.example.semenov_lesson.dto.VacanciesPost

class VacanciesFragment : Fragment() {

    companion object {
        fun newInstance() = VacanciesFragment()
        const val GENERATED_POSTS_AMOUNT = 20
    }

    private val openSearch = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentVacanciesBinding.inflate(layoutInflater, container, false).also { binding ->
        val adapter = VacanciesAdapter()
        binding.vacanciesRecyclerView.adapter = adapter

        data.observe(viewLifecycleOwner) { recipes ->
            adapter.setData(recipes.toMutableList())
        }


        openSearch.observe(viewLifecycleOwner) { openSearch ->
            if (openSearch) {
                binding.searchView.visibility = View.VISIBLE
            } else {
                binding.searchView.visibility = View.GONE
            }

            binding.searchView.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if (adapter.filter(newText)) {
                        binding.feedNotFound.visibility = View.VISIBLE
                    } else {
                        binding.feedNotFound.visibility = View.GONE
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {

                    return false
                }
            }
            )
        }
    }.root

    private val data: LiveData<List<VacanciesPost>> = MutableLiveData(
        List(GENERATED_POSTS_AMOUNT) { index ->
            VacanciesPost(
                id = index + 1,
                vacanciesName = "Android developer",
                vacanciesText = "Санкт-Петербург"
            )
        })

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.vacancies_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSearchButton -> {
                if (openSearch.value != null) {
                    openSearch.value = !openSearch.value!!
                }
                true
            }
            else -> false
        }
    }
}