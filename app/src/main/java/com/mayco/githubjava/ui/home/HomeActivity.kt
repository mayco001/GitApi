package com.mayco.githubjava.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        initFragment()
//        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.primeiro -> {
                Toast.makeText(this, "Primeiro", Toast.LENGTH_LONG).show()
                true
            }

            R.id.segundo -> {
                Toast.makeText(this, "Segundo", Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initFragment() {

        val frameLayout = GitHubFragment()
        val transition = supportFragmentManager.beginTransaction()

        transition.replace(R.id.containerID, frameLayout)

        transition.commit()
    }

    private fun initView() {
        binding.viewmodel = viewModel
        viewModel.getApi()
    }
}
