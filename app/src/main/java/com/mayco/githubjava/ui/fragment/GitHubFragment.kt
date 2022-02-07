package com.mayco.githubjava.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayco.githubjava.adapter.RecyclerViewAdapter
import com.mayco.githubjava.databinding.GitHubFragmentBinding
import com.mayco.githubjava.network.response.GitHubResponse
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitHubFragment : Fragment() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private val viewModel: GitHubViewModel by viewModel()

    private lateinit var binding: GitHubFragmentBinding

    private var numPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitHubFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initView()
//        initViewModel()
    }

    private fun initView() {

        binding.gitRecycler.layoutManager = LinearLayoutManager(context)
        binding.gitRecycler.adapter = recyclerViewAdapter

        binding.gitRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!canScrollVertically(
                        recyclerView,
                        1
                    ) && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    numPage += 15
                    viewModel.getGitHubPage(numPage)
                }
            }
        })
    }

    private fun initViewModel() {
        binding.viewmodel = viewModel
        viewModel.initViewModel()

        viewModel.gitList.observe(
            requireActivity(),
            Observer<List<GitHubResponse>> {
                recyclerViewAdapter.items = it
            }
        )
    }
}
