package com.example.viewmodelcontracts.username

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewmodelcontracts.R
import com.example.viewmodelcontracts.parentViewModel
import com.example.viewmodelcontracts.registerParentViewModel
import com.google.android.material.textfield.TextInputEditText


class UsernameEntryFragment : Fragment() {

    private lateinit var viewModel: UsernameEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.username_entry_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userNameEntry = view.findViewById<TextInputEditText>(R.id.user_name_entry)

        userNameEntry.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
                val username = input?.toString() ?: ""
                viewModel.updateUsername(username)
            }

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = parentViewModel()
    }

    companion object {
        fun <T : UsernameEntryViewModel> newInstance(parentViewModel: Class<T>): UsernameEntryFragment {
            return UsernameEntryFragment().apply {
                arguments = registerParentViewModel(parentViewModel)
            }
        }
    }
}