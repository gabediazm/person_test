package com.gdiaz.interviewtest.repository

import com.gdiaz.interviewtest.network.ResultData
import com.gdiaz.interviewtest.models.PersonDetails
import com.gdiaz.interviewtest.models.Persons


interface AppRepository {
    suspend fun getIds(): ResultData<Persons>
    suspend fun getPerson(id: String): ResultData<PersonDetails>
}