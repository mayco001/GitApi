package com.mayco.githubjava.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mayco.githubjava.R
import com.mayco.githubjava.databinding.ActivityHomeBinding
import com.mayco.githubjava.ui.fragment.GitHubFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        initFragment()
        initView()
    }

    private fun initFragment() {
        val frameLayout = GitHubFragment()
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.containerID, frameLayout)
        transition.commit()
    }

    private fun initView() {
        viewModel.getApi()
    }
}
