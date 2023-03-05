package com.example.semenov_lesson.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.semenov_lesson.MainActivity
import com.example.semenov_lesson.databinding.FragmentThisOfficeBinding


private const val OFFICE_NAME = "office name"
private const val OFFICE_ADDRESS = "office address"

class ThisOfficeFragment : Fragment() {

    companion object {
        fun newInstance(officeName: String, officeAddress: String) = ThisOfficeFragment().apply {
            arguments = Bundle().apply {
                putString(OFFICE_NAME, officeName)
                putString(OFFICE_ADDRESS, officeAddress)
            }
        }
    }

    private var officeName: String? = null
    private var officeAddress: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            officeName = it.getString(OFFICE_NAME)
            officeAddress = it.getString(OFFICE_ADDRESS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentThisOfficeBinding.inflate(layoutInflater, container, false).also { binding ->

        binding.officeName.text = officeName
        binding.officeAddress.text = officeAddress

        val actionBar = (requireActivity() as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
    }.root
}