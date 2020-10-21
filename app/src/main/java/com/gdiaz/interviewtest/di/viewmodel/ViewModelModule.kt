package com.gdiaz.interviewtest.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdiaz.interviewtest.ui.list_persons.PersonListViewModel
import com.gdiaz.interviewtest.ui.main.MainViewModel
import com.gdiaz.interviewtest.ui.view_person.PersonDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PersonListViewModel::class)
    abstract fun bindPersonListViewModel(personListViewModel: PersonListViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}