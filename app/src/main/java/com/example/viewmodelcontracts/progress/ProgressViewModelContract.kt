package com.example.viewmodelcontracts.progress

import androidx.lifecycle.LiveData

data class Progress(
    val userName: Boolean = false,
    val email: Boolean = false,
    val genres: Boolean = false
)

interface ProgressViewModelContract {

    val registrationProgress: LiveData<Progress>

}