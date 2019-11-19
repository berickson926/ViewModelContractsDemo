package com.example.viewmodelcontracts

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.viewmodelcontracts.email.EmailEntryFragment
import com.example.viewmodelcontracts.genre.GenreSelectionFragment
import com.example.viewmodelcontracts.registrationprogress.RegistrationProgressFragment
import com.example.viewmodelcontracts.username.UsernameEntryFragment

class RegistrationActivity : AppCompatActivity() {

    private val viewModel by viewModels<RegistrationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        setupRegistrationProgress()
        setupRegistrationFlow()

        findViewById<Button>(R.id.next_button).setOnClickListener {
            viewModel.onNext()
        }

        findViewById<Button>(R.id.back_button).setOnClickListener {
            viewModel.onBack()
        }
    }

    private fun setupRegistrationProgress() {
        val registrationProgress = RegistrationProgressFragment.newInstance(viewModel::class.java)
        supportFragmentManager.beginTransaction()
            .add(R.id.registration_progress, registrationProgress)
            .commit()
    }

    private fun setupRegistrationFlow() {
        viewModel.registrationState.observe(this, Observer { state ->
            var registrationStep: Fragment? = null
            when (state) {
                is RegistrationState.UserNameEntry ->  {
                    registrationStep = UsernameEntryFragment.newInstance(viewModel::class.java)
                }
                is RegistrationState.EmailEntry -> {
                    registrationStep = EmailEntryFragment.newInstance(viewModel::class.java)
                }
                is RegistrationState.GenreSelection -> {
                    displayRegistrationStep(GenreSelectionFragment())
                }
            }

            registrationStep?.let {
                displayRegistrationStep(it)
            }
        })
    }

    private fun displayRegistrationStep(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.registration_step_container, fragment)
            .commit()
    }

}
