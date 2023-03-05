package com.example.semenov_lesson.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.semenov_lesson.MainActivity
import com.example.semenov_lesson.R
import com.example.semenov_lesson.adapter.OfficesAdapter
import com.example.semenov_lesson.databinding.FragmentOfficesBinding
import com.example.semenov_lesson.dto.OfficesPost

class OfficesFragment : Fragment() {

    companion object {
        fun newInstance() = OfficesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentOfficesBinding.inflate(layoutInflater, container, false).also { binding ->
        val adapter = OfficesAdapter()
        binding.officesRecyclerView.adapter = adapter
        adapter.submitList(getListOfOffices(requireContext()))
        adapter.clickListener = { officesPost ->
            (requireActivity() as MainActivity).openOffice(officesPost)
        }
    }.root

    private fun getListOfOffices(context: Context): List<OfficesPost> {
        return listOf(
            OfficesPost(
                5,
                context.getString(R.string.officeMoscow),
                context.getString(R.string.addressOfficeMoscow)
            ),
            OfficesPost(
                4,
                context.getString(R.string.officeKazan),
                context.getString(R.string.addressOfficeKazan)
            ),
            OfficesPost(
                3,
                context.getString(R.string.officeRostovOnDon),
                context.getString(R.string.addressOfficeRostovOnDon)
            ),
            OfficesPost(
                2,
                context.getString(R.string.officeMinsk),
                context.getString(R.string.addressOfficeMinsk)
            ),
            OfficesPost(
                1,
                context.getString(R.string.officeGomel),
                context.getString(R.string.addressOfficeGomel)
            )
        )
    }
}