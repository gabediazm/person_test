package com.gdiaz.interviewtest.ui.list_persons

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gdiaz.interviewtest.MainApplication
import com.gdiaz.interviewtest.R
import com.gdiaz.interviewtest.databinding.PersonListFragmentBinding
import com.gdiaz.interviewtest.models.Person
import com.gdiaz.interviewtest.models.PersonDetails
import com.gdiaz.interviewtest.ui.view_person.PersonDetailsActivity
import com.gdiaz.interviewtest.utils.PERSON_DETAILS_EXTRA
import com.gdiaz.interviewtest.utils.hide
import com.gdiaz.interviewtest.utils.show
import com.gdiaz.interviewtest.utils.viewModelProvider
import javax.inject.Inject

class PersonListFragment : Fragment(),
    PersonItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var personsAdapter: PersonsAdapter
    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): PersonListViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: PersonListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        appComponents.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.person_list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        getViewModel().getIds()
        binding.swipeRefresh.setOnRefreshListener(this)
        personsAdapter = PersonsAdapter(this)
    }

    private fun initObservers() {
        getViewModel().person.observe(viewLifecycleOwner, { person ->
            loadRecycler(person)
        })

        getViewModel().ids.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false

            it.ids.forEach { id ->
                getViewModel().getPersonData(id)
            }
        })

        getViewModel().errorMessageList.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false
            handleNoResults()
        })
        getViewModel().showLoading.observe(viewLifecycleOwner, { showLoading ->
            if (showLoading) binding.progress.show()
            else binding.progress.hide()
        })

        getViewModel().errorMessagePerson.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false
        })
    }

    private fun loadRecycler(person: PersonDetails) {
        if (!personsAdapter.list.any { p -> p.details.id == person.details.id }) {
            personsAdapter.list.add(person)
        }

        personsAdapter.notifyDataSetChanged()

        with(binding){
            recyclerListPersons.show()
            tvErrorMessage.hide()
            binding.recyclerListPersons.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = personsAdapter
            }
        }
    }

    private fun handleNoResults() {
        with(binding) {
            recyclerListPersons.hide()
            tvErrorMessage.show()
            tvErrorMessage.text = getString(R.string.no_persons_available)
        }
    }


    override fun onRefresh() {
        personsAdapter.list.clear()
        getViewModel().getIds()
    }

    override fun onPersonItemClicked(person: Person) {
        Intent(activity, PersonDetailsActivity::class.java).apply {
            putExtra(PERSON_DETAILS_EXTRA, person)
            startActivity(this)
        }
    }
}
