package com.example.kamteamapp.Utils

import com.example.kamteamapp.data.User

object MyHelper {
    private const val USER = "user"

    suspend fun getUser(): User {
        return User()
    }

}