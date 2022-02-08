package com.mayco.githubjava.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayco.githubjava.adapter.RecyclerViewAdapter
import com.mayco.githubjava.databinding.GitHubFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitHubFragment : Fragment() {

    private var recyclerViewAdapter = RecyclerViewAdapter()
    private val viewModel: GitHubViewModel by viewModel()
    private lateinit var binding: GitHubFragmentBinding
    private var recyclerView: RecyclerView? = null
    private var numPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GitHubFragmentBinding.inflate(inflater, container, false)
        recyclerView = binding.gitRecycler
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = recyclerViewAdapter

            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!canScrollVertically(
                            recyclerView,
                            1
                        ) && newState == RecyclerView.SCROLL_STATE_IDLE
                    ) {

                        viewModel.getGitHubPage()
                    }
                }
            })
        }
    }

    private fun initViewModel() {
        viewModel.initViewModel()

        viewModel.gitList.observe(
            requireActivity()
        ) {
            recyclerViewAdapter.items = it
        }
    }
}
