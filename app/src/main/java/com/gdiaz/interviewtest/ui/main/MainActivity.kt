package com.gdiaz.interviewtest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gdiaz.interviewtest.MainApplication
import com.gdiaz.interviewtest.R
import com.gdiaz.interviewtest.databinding.ActivityMainBinding
import com.gdiaz.interviewtest.ui.list_persons.PersonListFragment
import com.gdiaz.interviewtest.utils.loadFragment
import com.gdiaz.interviewtest.utils.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): MainViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        loadPersons()
    }

    private fun loadPersons() {
        loadFragment(PersonListFragment(), R.id.frame_layout_container)
    }
}