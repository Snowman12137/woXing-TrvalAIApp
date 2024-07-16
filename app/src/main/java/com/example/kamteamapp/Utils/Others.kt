package com.example.kamteamapp.Utils

fun String.toCustomBoolean(): Boolean {
    return when (this.toLowerCase()) {
        "true", "yes", "1" -> true
        "false", "no", "0" -> false
        else -> throw IllegalArgumentException("Invalid boolean value")
    }
}