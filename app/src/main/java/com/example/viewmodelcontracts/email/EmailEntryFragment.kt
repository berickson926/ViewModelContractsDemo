package com.example.viewmodelcontracts.email

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewmodelcontracts.R
import com.example.viewmodelcontracts.viewModelContract
import com.example.viewmodelcontracts.registerViewModelContract
import com.google.android.material.textfield.TextInputEditText


class EmailEntryFragment : Fragment() {

    private lateinit var viewModel: EmailEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.email_entry_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userNameEntry = view.findViewById<TextInputEditText>(R.id.email_entry)

        userNameEntry.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(input: Editable?) {
                val email = input?.toString() ?: ""
                viewModel.updateEmail(email)
            }

            override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelContract()
    }

    companion object {

        fun <T : EmailEntryViewModel> newInstance(parentViewModel: Class<T>): EmailEntryFragment {
            return EmailEntryFragment().apply {
                arguments = registerViewModelContract(parentViewModel)
            }
        }
    }
}