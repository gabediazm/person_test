package com.gdiaz.interviewtest.di.component

import android.content.Context
import com.gdiaz.interviewtest.di.module.AppModule
import com.gdiaz.interviewtest.di.module.CoroutinesModule
import com.gdiaz.interviewtest.di.module.RepositoryModule
import com.gdiaz.interviewtest.di.module.RetrofitModule
import com.gdiaz.interviewtest.ui.main.MainActivity
import com.gdiaz.interviewtest.ui.list_persons.PersonListFragment
import com.gdiaz.interviewtest.ui.view_person.PersonDetailsActivity
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class,
        RepositoryModule::class,
        CoroutinesModule::class
    ]
)
interface AppComponents {
    fun context(): Context

    fun retrofit(): Retrofit

    fun inject(mainActivity: MainActivity)
    fun inject(personListFragment: PersonListFragment)
    fun inject(personDetailsActivity: PersonDetailsActivity)

}