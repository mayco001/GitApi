package com.mayco.githubjava.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mayco.githubjava.network.ApiService
import com.mayco.githubjava.repository.GitHubRepository
import com.mayco.githubjava.ui.fragment.GitHubViewModel
import com.mayco.githubjava.ui.home.HomeViewModel
import com.mayco.githubjava.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    single { provideRetrofit(get()) }
    single { provideOkHttp(get()) }
    single { provideHttpLoggingInterceptor() }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { GitHubViewModel(get()) }
}

val repositoryModule = module {
    factory { GitHubRepository(get()) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.apply {
        addInterceptor(httpLoggingInterceptor)
    }
    return okHttpClient.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(ApiService::class.java)
}
