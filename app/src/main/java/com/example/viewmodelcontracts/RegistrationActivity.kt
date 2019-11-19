package com.example.viewmodelcontracts

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
            when (state) {
                is RegistrationState.UserNameEntry -> {
                    val fragment = UsernameEntryFragment.newInstance(viewModel::class.java)
                    displayRegistrationStep(fragment)
                }
                is RegistrationState.EmailEntry -> {
                    val fragment = EmailEntryFragment.newInstance(viewModel::class.java)
                    displayRegistrationStep(fragment)
                }
                is RegistrationState.GenreSelection -> {
                    val fragment = GenreSelectionFragment.newInstance(viewModel::class.java)
                    displayRegistrationStep(fragment)
                }
                is RegistrationState.GenreSubmission -> {
                    requestGenreSelections()
                }
                is RegistrationState.Complete -> {
                    displayRegistrationResults(state.userData)
                }
            }
        })
    }

    private fun displayRegistrationStep(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.registration_step_container, fragment)
            .commit()
    }

    private fun requestGenreSelections() {
        val genreSelectionFragment =
            supportFragmentManager.findFragmentById(R.id.registration_step_container) as GenreSelectionFragment
        genreSelectionFragment.submitGenreSelections()
    }

    private fun displayRegistrationResults(userData: RegistrationData) {
        AlertDialog.Builder(this)
            .setTitle("Registration Results")
            .setMessage(userData.toString())
            .setPositiveButton("Ok", null)
            .show()
    }

}
