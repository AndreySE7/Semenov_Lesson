package com.example.semenov_lesson.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.semenov_lesson.databinding.FragmentPostOfficeBinding
import com.example.semenov_lesson.databinding.FragmentThisOfficeBinding
import com.example.semenov_lesson.dto.OfficesPost

class OfficesAdapter : RecyclerView.Adapter<OfficesAdapter.OfficesViewHolder>() {

    private var listOffices = emptyList<OfficesPost>()
    var clickListener: ((OfficesPost)->Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<OfficesPost>) {
        listOffices = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfficesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentPostOfficeBinding.inflate(inflater, parent, false)
        return OfficesViewHolder(binding)
    }

    override fun getItemCount() = listOffices.size

    override fun onBindViewHolder(holder: OfficesViewHolder, position: Int) {
        holder.populate(listOffices[position], clickListener)
    }

    class OfficesViewHolder(
        private val binding: FragmentPostOfficeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun populate(officesPost: OfficesPost, listener: ((OfficesPost) -> Unit)?) {
            binding.title.text = officesPost.officesName
            binding.officeButton.setOnClickListener {
                listener?.invoke(officesPost)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listOffices[position].id
    }
}