package com.gdiaz.interviewtest.repository

import com.gdiaz.interviewtest.di.IoDispatcher
import com.gdiaz.interviewtest.network.PersonService
import com.gdiaz.interviewtest.network.ResultData
import com.gdiaz.interviewtest.models.PersonDetails
import com.gdiaz.interviewtest.models.Persons
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val personService: PersonService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    override suspend fun getIds(): ResultData<Persons> {
        return withContext(ioDispatcher) {
            val request = personService.getIds()
            ResultData.Success(request)
        }
    }

    override suspend fun getPerson(id: String): ResultData<PersonDetails> {
        return withContext(ioDispatcher) {
            val request = personService.getPerson(id)
            ResultData.Success(request)
        }
    }

}