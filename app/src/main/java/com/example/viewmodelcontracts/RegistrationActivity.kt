package com.example.viewmodelcontracts

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.viewmodelcontracts.email.EmailEntryFragment
import com.example.viewmodelcontracts.genre.GenreSelectionFragment
import com.example.viewmodelcontracts.username.UsernameEntryFragment

class RegistrationActivity : AppCompatActivity() {

    private val viewModel by viewModels<RegistrationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)
        setupRegistrationFlow()

        findViewById<Button>(R.id.next_button).setOnClickListener {
            viewModel.onNext()
        }

        findViewById<Button>(R.id.back_button).setOnClickListener {
            viewModel.onBack()
        }
    }

    private fun setupRegistrationFlow() {
        viewModel.registrationState.observe(this, Observer { state ->
            when (state) {
                is RegistrationState.UserNameEntry ->  {
                    displayRegistrationStep(UsernameEntryFragment())
                }
                is RegistrationState.EmailEntry -> {
                    displayRegistrationStep(EmailEntryFragment())
                }
                is RegistrationState.GenreSelection -> {
                    displayRegistrationStep(GenreSelectionFragment())
                }
            }
        })
    }

    private fun displayRegistrationStep(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.registration_step_container, fragment)
            .commit()
    }

}
