package com.example.viewmodelcontracts.interest

import androidx.lifecycle.LiveData


interface InterestSubmissionViewModel {

    val shouldSubmitGenreSelections: LiveData<Boolean>

    fun submitGenreSelections(genres: List<String>)
}