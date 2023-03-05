package com.example.semenov_lesson.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.semenov_lesson.databinding.FragmentPostVacancieBinding
import com.example.semenov_lesson.dto.VacanciesPost
import java.util.*

class VacanciesAdapter :
    ListAdapter<VacanciesPost, VacanciesAdapter.VacanciesViewHolder>(DiffCallBack) {

    private var list = mutableListOf<VacanciesPost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentPostVacancieBinding.inflate(inflater, parent, false)
        return VacanciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(list: MutableList<VacanciesPost>?) {
        this.list = list!!
        submitList(list)
    }

    class VacanciesViewHolder(
        private val binding: FragmentPostVacancieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var vacanciesPost: VacanciesPost

        fun bind(vacanciesPost: VacanciesPost) {
            this.vacanciesPost = vacanciesPost

            with(binding) {
                title.text = vacanciesPost.vacanciesName
                subtitle.text = vacanciesPost.vacanciesText
            }
        }
    }

    private object DiffCallBack : DiffUtil.ItemCallback<VacanciesPost>() {
        override fun areItemsTheSame(oldItem: VacanciesPost, newItem: VacanciesPost) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: VacanciesPost, newItem: VacanciesPost) =
            oldItem == newItem

    }

    fun filter(query: CharSequence?): Boolean {
        val filteredList = mutableListOf<VacanciesPost>()
        if (query == null || query.isEmpty()) {
            filteredList.addAll(list)
        } else {
            for (item in list) {
                if (
                    item.vacanciesName
                        .lowercase(Locale.getDefault())
                        .contains(query.toString().lowercase(Locale.getDefault()))
                ) {
                    filteredList.add(item)
                }
            }
        }
        submitList(filteredList)
        return filteredList.isNullOrEmpty()
    }
}