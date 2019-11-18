package com.example.viewmodelcontracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.viewmodelcontracts.registrationprogress.Progress
import com.example.viewmodelcontracts.registrationprogress.RegistrationProgressViewModel


class RegistrationViewModel : ViewModel(), RegistrationProgressViewModel {

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
        _registrationState.postValue(RegistrationState.UserNameEntry(RegistrationData()))
    }

    fun onNext() {
        //TODO: child frag VM must approve of text entry before proceeding
        _registrationState.value = RegistrationState.EmailEntry(RegistrationData())
    }

    //TODO: maintain reg user data across steps
    fun onBack() {
        when (_registrationState.value) {
            is RegistrationState.EmailEntry -> {
                _registrationState.value = RegistrationState.UserNameEntry(RegistrationData())
            }
            is RegistrationState.GenreSelection -> {
                _registrationState.value = RegistrationState.EmailEntry(RegistrationData())
            }
            else -> { /* no-op */ }
        }
    }
}