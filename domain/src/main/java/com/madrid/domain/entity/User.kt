package com.madrid.domain.entity
data class User(
    val id: String,
    val username: String,
    val email: String?,
    val profilePicUrl: String?,
    val authToken: String?,
    val isVerified: Boolean = false,
    val twoFactorEnabled: Boolean = false,
    val isGuest: Boolean = false
)