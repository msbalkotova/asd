package kz.diploma.workgram.di

import kz.diploma.workgram.repositories.AuthRepository
import kz.diploma.workgram.repositories.HomeRepository
import org.koin.dsl.module

val repositoryModule = module{
    single { AuthRepository(get(), get()) }
    single { HomeRepository(get(), get()) }
}