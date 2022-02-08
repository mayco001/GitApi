package com.mayco.githubjava.repository

import com.mayco.githubjava.network.ApiService
import com.mayco.githubjava.network.response.GitHubResponse
import com.orhanobut.hawk.Hawk

class GitHubRepository(private val apiService: ApiService) {

    suspend fun getGitHub(limit: Int, start: Int) = apiService.getReps(limit, start).await()

    fun saveApi(save: List<GitHubResponse?>) {
        Hawk.put("save", save)
    }

    fun getApi(): List<GitHubResponse>? {
        return Hawk.get("save")
    }
}
