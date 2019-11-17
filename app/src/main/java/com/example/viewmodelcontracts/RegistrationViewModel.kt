package com.example.viewmodelcontracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class RegistrationViewModel : ViewModel() {

    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState> = _registrationState

    init {
        _registrationState.postValue(RegistrationState.UserNameEntry(RegistrationData()))
    }

    override fun onCleared() {
        super.onCleared()
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