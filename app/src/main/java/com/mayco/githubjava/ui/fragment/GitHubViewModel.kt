package com.mayco.githubjava.ui.fragment

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

class GitHubViewModel(private val repository: GitHubRepository) : ViewModel(), CoroutineScope {

    private val _gitList = MutableLiveData<List<GitHubResponse>>()
    val gitList: LiveData<List<GitHubResponse>>
        get() = _gitList

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initViewModel() {
        repository.getApi()?.let {
            _gitList.postValue(it)
        }
    }

    fun getGitHubPage() {
        launch {
            try {
                val response = repository.getGitHub(20, 0)
                if (response.isSuccessful) {
                    val listApi = response.body()!!
                    val listCache = repository.getApi()
                    val listActual = arrayListOf<GitHubResponse>()

                    listCache?.let { listActual.addAll(it) }
                    listActual.addAll(listApi)

                    _gitList.postValue(listActual)
                }
            } catch (e: Exception) {
                print(e)
            }
        }
    }
}
