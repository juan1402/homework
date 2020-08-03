package com.sweatworks.homework
import android.app.Application
import com.sweatworks.data.di.databaseModule
import com.sweatworks.data.di.networkingModule
import com.sweatworks.data.di.repositoryModule
import com.sweatworks.domain.di.interactionModule
import com.sweatworks.homework.di.appModule
import com.sweatworks.homework.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HomeWorkApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HomeWorkApp)
            modules(appModules + domainModules + dataModules)
        }
    }
}

val appModules = listOf(presentationModule, appModule)
val domainModules = listOf(interactionModule)
val dataModules = listOf(networkingModule, repositoryModule, databaseModule)