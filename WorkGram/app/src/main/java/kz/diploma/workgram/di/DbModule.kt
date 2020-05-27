package kz.diploma.workgram.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import kz.diploma.workgram.utils.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single { provideSharedPreferences(androidApplication()) }
}

fun provideSharedPreferences(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(Constants.APP_PREF, Context.MODE_PRIVATE)
}
