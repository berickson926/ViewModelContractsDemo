package com.example.viewmodelcontracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.viewmodelcontracts.registrationprogress.Progress
import com.example.viewmodelcontracts.registrationprogress.RegistrationProgressViewModel
import com.example.viewmodelcontracts.username.UsernameEntryViewModel


class RegistrationViewModel : ViewModel(), RegistrationProgressViewModel, UsernameEntryViewModel {

    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState> = _registrationState

    private val _userData = MutableLiveData<RegistrationData>()

    override val registrationProgress: LiveData<Progress> = Transformations.map(_userData) {
        data: RegistrationData -> Progress(
            userName = data.username.isNotBlank(),
            email = data.email.isNotBlank(),
            genres = data.genres.isNotEmpty()
        )
    }

    init {
        _registrationState.value = RegistrationState.UserNameEntry
        _userData.value = RegistrationData()
    }

    override fun updateUsername(username: String) {
        _userData.value = _userData.value?.apply {
            this.username = username
        }
    }

    fun onNext() {
        //TODO: child frag VM must approve of text entry before proceeding
        _registrationState.value = RegistrationState.EmailEntry
    }

    fun onBack() {
        when (_registrationState.value) {
            is RegistrationState.EmailEntry -> {
                _registrationState.value = RegistrationState.UserNameEntry
            }
            is RegistrationState.GenreSelection -> {
                _registrationState.value = RegistrationState.EmailEntry
            }
            else -> { /* no-op */ }
        }
    }
}