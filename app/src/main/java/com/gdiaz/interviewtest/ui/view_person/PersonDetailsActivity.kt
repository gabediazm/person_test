package com.gdiaz.interviewtest.ui.view_person

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gdiaz.interviewtest.MainApplication
import com.gdiaz.interviewtest.R
import com.gdiaz.interviewtest.databinding.ActivityPersonDetailsBinding
import com.gdiaz.interviewtest.models.Person
import com.gdiaz.interviewtest.utils.PERSON_DETAILS_EXTRA
import com.gdiaz.interviewtest.utils.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PersonDetailsActivity : AppCompatActivity() {
    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): PersonDetailsViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: ActivityPersonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_person_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    private fun initViews() {
        intent?.let {
            it.getSerializableExtra(PERSON_DETAILS_EXTRA)?.let { response ->
              loadUi(response as Person)
            }
        }
    }

    private fun loadUi(person: Person) {
        with(binding) {
            tvFirstName.text = person.firstName
            tvLastName.text = person.lastName
            tvAge.text = person.age
            tvGender.text = person.gender
            tvCountry.text = person.country
        }
    }
}