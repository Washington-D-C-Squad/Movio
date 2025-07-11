package com.example.data.dataSource.remote

interface SearchRemoteSource {
    suspend fun searchMultiMovieDataByName(name: String, language: String ,)
    suspend fun searchPersonByName(name: String, language: String )
}