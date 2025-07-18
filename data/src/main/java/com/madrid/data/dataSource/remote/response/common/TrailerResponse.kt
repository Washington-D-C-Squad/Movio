package com.madrid.data.dataSource.remote.response.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerResponse(
    val id: Int,
    val results: List<TrailerResult>
)

@Serializable
data class TrailerResult(
    @SerialName("key")
    val key: String,
    @SerialName("id")
    val id: String
)
