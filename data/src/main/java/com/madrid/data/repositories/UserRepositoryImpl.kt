package com.madrid.data.repositories

import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.User
import com.madrid.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : UserRepository {

    override suspend fun login(
        username: String,
        password: String
    ): User {
        remoteDataSource.login(username, password)
        return User(
            id = "12345",
            username = username,
            email = null,
            profilePicUrl = null,
            authToken = "dummy_token",
            isGuest = false
        )
    }

    override suspend fun register(
        email: String,
        password: String,
        username: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): User? {
        TODO("Not yet implemented")
    }

    override suspend fun isUserLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): Boolean {
        TODO("Not yet implemented")
    }



    override suspend fun sendPasswordResetEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserProfile(user: User): User {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isTokenExpired(token: String?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun loginAsGuest(): User {
        val guest = remoteDataSource.loginAsGuest()
        return User(
            id = "guest",
            username = "Guest",
            email = null,
            profilePicUrl = null,
            authToken = "",
            isGuest = true
        )
    }
}