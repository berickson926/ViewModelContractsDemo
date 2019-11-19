package com.example.viewmodelcontracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.viewmodelcontracts.email.EmailEntryViewModel
import com.example.viewmodelcontracts.registrationprogress.Progress
import com.example.viewmodelcontracts.registrationprogress.RegistrationProgressViewModel
import com.example.viewmodelcontracts.username.UsernameEntryViewModel


class RegistrationViewModel : ViewModel(),
    RegistrationProgressViewModel,
    UsernameEntryViewModel,
    EmailEntryViewModel {

    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState> = _registrationState

    override val registrationProgress: LiveData<Progress> = Transformations.map(_registrationState) {
        state: RegistrationState -> Progress(
            userName = state.userData.username.isNotBlank(),
            email = state.userData.email.isNotBlank(),
            genres = state.userData.genres.isNotEmpty()
        )
    }

    init {
        _registrationState.value = RegistrationState.UserNameEntry(RegistrationData())
    }

    override fun updateUsername(username: String) {
        _registrationState.value?.userData?.username = username
    }

    override fun updateEmail(email: String) {
        _registrationState.value?.userData?.email = email
    }

    fun onNext() {
        val userData = _registrationState.value?.userData ?: RegistrationData()
        when (_registrationState.value) {
            is RegistrationState.UserNameEntry -> {
                _registrationState.value = RegistrationState.EmailEntry(userData)
            }
            is RegistrationState.EmailEntry -> {
                _registrationState.value = RegistrationState.GenreSelection(userData)
            }
            // Done?
        }
    }

    fun onBack() {
        when (_registrationState.value) {
            is RegistrationState.EmailEntry -> {
                _registrationState.value = RegistrationState.UserNameEntry(RegistrationData())
            }
            is RegistrationState.GenreSelection -> {
                _registrationState.value = RegistrationState.EmailEntry(RegistrationData())
            }
        }
    }
}