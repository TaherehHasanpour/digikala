package com.example.digikala.util

import androidx.core.text.isDigitsOnly

object InputValidation {

    fun isValidPhoneNumber(number: String): Boolean {
        return number.isNotEmpty() &&
                number.isNotBlank() &&
                number.isDigitsOnly() &&
                number.startsWith("09") &&
                number.length == 11
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.isNotBlank() && password.length >= 6
    }
}