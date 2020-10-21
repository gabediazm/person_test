package com.gdiaz.interviewtest.di.module

import com.gdiaz.interviewtest.di.IoDispatcher
import com.gdiaz.interviewtest.network.PersonService
import com.gdiaz.interviewtest.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        personService: PersonService
    ): AppRepositoryImpl {

        return AppRepositoryImpl(personService, ioDispatcher)
    }
}