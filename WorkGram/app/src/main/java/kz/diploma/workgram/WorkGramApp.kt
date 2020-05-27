package kz.diploma.workgram

import android.app.Application
import android.content.Context
import kz.diploma.workgram.di.dbModule
import kz.diploma.workgram.di.networkModule
import kz.diploma.workgram.di.repositoryModule
import kz.diploma.workgram.di.viewModelModule
import kz.diploma.workgram.utils.LocaleManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WorkGramApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WorkGramApp)
            modules(
                listOf(
                    dbModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleManager.onAttach(base, "kk"))
    }
}