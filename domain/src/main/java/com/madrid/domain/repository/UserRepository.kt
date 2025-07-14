package com.madrid.domain.repository

interface UserRepository {
    suspend fun isUserLoggedIn(): Boolean

}