package com.example.viewmodelcontracts


class RegistrationData(
    var username: String = "",
    var email: String = "",
    var interests: List<String> = listOf()
) {
    override fun toString(): String {
        return """
            Username: $username
            Email: $email
            Interests: $interests
        """.trimIndent()
    }
}