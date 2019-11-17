package com.example.viewmodelcontracts


sealed class RegistrationState(userData: RegistrationData) {

    class UserNameEntry(userData: RegistrationData) : RegistrationState(userData)

    class EmailEntry(userData: RegistrationData) : RegistrationState(userData)

    class GenreSelection(userData: RegistrationData) : RegistrationState(userData)

}