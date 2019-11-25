package com.example.viewmodelcontracts.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.viewmodelcontracts.R
import com.example.viewmodelcontracts.registerViewModelContract
import com.example.viewmodelcontracts.viewModelContract

class ProgressFragment : Fragment() {

    private lateinit var viewModelContract: ProgressViewModelContract

    private val userNameComplete by lazy { view?.findViewById<ImageView>(R.id.user_name_state) }
    private val emailComplete by lazy { view?.findViewById<ImageView>(R.id.email_state) }
    private val genreComplete by lazy { view?.findViewById<ImageView>(R.id.genre_selection_state) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registration_progress_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelContract = viewModelContract()

        viewModelContract.registrationProgress.observe(this, Observer {
            userNameComplete?.setVisibility(it.userName)
            emailComplete?.setVisibility(it.email)
            genreComplete?.setVisibility(it.genres)
        })
    }

    private fun View.setVisibility(visible: Boolean) {
        // naively set visibility regardless of current view state
        visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    companion object {
        fun <T: ProgressViewModelContract> newInstance(contract: Class<T>): ProgressFragment {
            return ProgressFragment().apply {
                arguments = registerViewModelContract(contract)
            }
        }
    }
}