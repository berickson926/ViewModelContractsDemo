package com.example.viewmodelcontracts.interest

import androidx.lifecycle.ViewModel


class InterestSelectionViewModel : ViewModel() {

    private val _genreSelections: MutableSet<String> = mutableSetOf()

    fun addSelection(genre: String) {
        _genreSelections.add(genre)
    }

    fun removeSelection(genre: String) {
        _genreSelections.remove(genre)
    }

    fun getSelections(): List<String> {
        return _genreSelections.toList()
    }
}