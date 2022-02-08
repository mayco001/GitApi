package com.mayco.githubjava

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.mayco.githubjava.di.applicationModule
import com.mayco.githubjava.di.repositoryModule
import com.mayco.githubjava.di.viewModelModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitHubApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupHawk()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GitHubApplication)
            androidFileProperties()
            modules(applicationModule, repositoryModule, viewModelModule)
        }
    }

    private fun setupHawk() = Hawk.init(this).build()

    companion object {
        lateinit var instace: Context
    }
}
