package com.example.viewmodelcontracts.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.viewmodelcontracts.R
import com.example.viewmodelcontracts.parentViewModel
import com.example.viewmodelcontracts.registerParentViewModel


class GenreSelectionFragment : Fragment() {

    private lateinit var parentViewModel: GenreSubmissionViewModel

    private val viewModel by viewModels<GenreSelectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.genre_section_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (view) {
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parentViewModel = parentViewModel()
    }

    fun submitGenreSelections() {
        parentViewModel.submitGenreSelections(viewModel.getSelections())
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

        fun <T : GenreSubmissionViewModel> newInstance(parentViewModel: Class<T>): GenreSelectionFragment {
            return GenreSelectionFragment().apply {
                arguments = registerParentViewModel(parentViewModel)
            }
        }
    }
}


