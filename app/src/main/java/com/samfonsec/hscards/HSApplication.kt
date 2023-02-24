package com.samfonsec.hscards

import android.app.Application
import com.samfonsec.hscards.data.di.cardModule
import com.samfonsec.hscards.data.di.infoModule
import com.samfonsec.hscards.data.di.networkModule
import com.samfonsec.hscards.domain.di.useCasesModule
import com.samfonsec.hscards.presentation.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class HSApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@HSApplication)
            modules(listOf(networkModule, infoModule, cardModule, useCasesModule, viewModelModule))
        }
    }
}
