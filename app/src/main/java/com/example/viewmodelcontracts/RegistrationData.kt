package com.example.viewmodelcontracts


data class RegistrationData(
    var username: String = "",
    var email: String = "",
    var genres: List<String> = listOf()
)