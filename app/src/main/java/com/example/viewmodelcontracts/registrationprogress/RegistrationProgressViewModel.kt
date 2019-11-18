package com.example.viewmodelcontracts.registrationprogress

import androidx.lifecycle.LiveData

data class Progress(
    val userName: Boolean = false,
    val email: Boolean = false,
    val genres: Boolean = false
)

interface RegistrationProgressViewModel {

    val registrationProgress: LiveData<Progress>
}