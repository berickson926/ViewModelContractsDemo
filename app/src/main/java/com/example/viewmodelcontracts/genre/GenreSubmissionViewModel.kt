package com.example.viewmodelcontracts.genre

import androidx.lifecycle.LiveData


interface GenreSubmissionViewModel {


    val shouldSubmitGenreSelections: LiveData<Boolean>

    fun submitGenreSelections(genres: List<String>)
}