package com.mayco.githubjava

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mayco.githubjava.di.applicationModule
import com.mayco.githubjava.di.repositoryModule
import com.mayco.githubjava.di.viewModelModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitHubAplication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instace = applicationContext

        setupHawk()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GitHubAplication)
            androidFileProperties()
            modules(applicationModule, repositoryModule, viewModelModule)
        }
    }

    private fun setupHawk() = Hawk.init(this).build()

    companion object {
        lateinit var instace: Context
    }
}
