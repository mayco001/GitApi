package com.mayco.githubjava.network

import com.mayco.githubjava.network.response.GitHubResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/users")
    fun getReps(
        @Query("_limit") limit: Int,
        @Query("_start") start: Int
    ): Deferred<Response<List<GitHubResponse>>>
}
