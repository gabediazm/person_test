package com.gdiaz.interviewtest.ui.list_persons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdiaz.interviewtest.models.PersonDetails
import com.gdiaz.interviewtest.network.ResultData
import com.gdiaz.interviewtest.models.Persons
import com.gdiaz.interviewtest.repository.AppRepositoryImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class PersonListViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    private var _ids = MutableLiveData<Persons>()
    var ids: LiveData<Persons> = _ids

    private var _person = MutableLiveData<PersonDetails>()
    var person: LiveData<PersonDetails> = _person

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessagePerson = MutableLiveData<String>()
    var errorMessagePerson: LiveData<String> = _errorMessagePerson

    private var _errorMessageList = MutableLiveData<String>()
    var errorMessageList: LiveData<String> = _errorMessageList

    fun getIds() {
        _showLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getIds()) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        _ids.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _showLoading.postValue(false)
                        _errorMessageList.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                _errorMessageList.postValue(e.message)
            }
        }
    }

    fun getPersonData(id: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getPerson(id)) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        _person.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _showLoading.postValue(false)
                        _errorMessagePerson.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                _errorMessagePerson.postValue(e.message)
            }
        }
    }
}