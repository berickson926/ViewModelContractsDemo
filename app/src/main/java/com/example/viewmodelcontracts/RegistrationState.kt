package com.example.viewmodelcontracts


sealed class RegistrationState {

    object UserNameEntry : RegistrationState()

    object EmailEntry : RegistrationState()

    object GenreSelection : RegistrationState()

}