package com.example.viewmodelcontracts.registrationprogress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.viewmodelcontracts.R
import com.example.viewmodelcontracts.parentViewModel


class RegistrationProgressFragment : Fragment() {

    private lateinit var viewModel: RegistrationProgressViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = parentViewModel()

        viewModel.registrationProgress.observe(this, Observer {
            userNameComplete?.setVisibility(it.userName)
            emailComplete?.setVisibility(it.email)
            genreComplete?.setVisibility(it.genres)
        })
    }

    private fun View.setVisibility(visible: Boolean) {
        if (visible && !isVisible) {
            visibility = View.VISIBLE
        } else if (!visible && isVisible) {
            visibility = View.GONE
        }
        // else, do nothing
    }
}