package com.hackheroes.newoldtown.common.ext

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() && this.length >= MIN_PASS_LENGTH
}

fun String.isValidDescription(): Boolean {
    return this.isNotBlank() && this.length >= 10
}

fun String.isValidTitle(): Boolean {
    return this.isNotBlank() && this.length >= 5
}