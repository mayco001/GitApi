package com.mayco.githubjava.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayco.githubjava.network.response.GitHubResponse
import com.mayco.githubjava.repository.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val repository: GitHubRepository) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val _apiList = MutableLiveData<List<GitHubResponse>>()
    val apiList: LiveData<List<GitHubResponse>>
        get() = _apiList

    fun getApi() {
        launch {
            try {

                val response = repository.getGitHub(15, (0..100).random())
                if (response.isSuccessful) {
                    repository.saveApi(response.body()!!)
                } else {
                    print(response)
                }
            } catch (e: Exception) {
                print("Error2")
            }
        }
    }
}
