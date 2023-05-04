package com.example.riverstonetask

import android.app.Application
import com.example.riverstonetask.repository.BookRepository
import com.example.riverstonetask.ui.pages.productlist.BookListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.gson.gson
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            androidLogger(Level.DEBUG)
            modules(appModule)
            modules(networkModule)
        }
    }
}

val appModule = module {
    single { BookRepository(get()) }
    viewModel { BookListViewModel(get()) }
}

val networkModule = module {
    single {
        HttpClient(Android) {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "dummyjson.com"
                }
            }
            install(ContentNegotiation) {
                gson()
            }
        }
    }
}
