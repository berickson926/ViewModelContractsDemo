package com.example.viewmodelcontracts.interest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.viewmodelcontracts.R
import com.example.viewmodelcontracts.registerViewModelContract
import com.example.viewmodelcontracts.viewModelContract


class InterestSelectionFragment : Fragment() {

    private lateinit var viewModelContract: InterestSubmissionViewModel

    private val viewModel by viewModels<InterestSelectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.genre_section_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelContract = viewModelContract()

        with(view) {
            findViewById<CheckBox>(R.id.android_label).setOnClickListener {
                setGenreSelection(it as CheckBox)
            }
            findViewById<CheckBox>(R.id.arch_components_label).setOnClickListener {
                setGenreSelection(it as CheckBox)
            }
            findViewById<CheckBox>(R.id.jetpack_compose_label).setOnClickListener {
                setGenreSelection(it as CheckBox)
            }
        }

        with(viewModelContract) {
            shouldSubmitGenreSelections.observe(this@InterestSelectionFragment) {
                submitGenreSelections(viewModel.getSelections())
            }
        }
    }

    private fun setGenreSelection(checkBox: CheckBox) {
        val selection = checkBox.text.toString()
        if (checkBox.isChecked) {
            viewModel.addSelection(selection)
        } else {
            viewModel.removeSelection(selection)
        }
    }

    companion object {

        fun <T : InterestSubmissionViewModel> newInstance(contract: Class<T>): InterestSelectionFragment {
            return InterestSelectionFragment().apply {
                arguments = registerViewModelContract(contract)
            }
        }
    }
}


