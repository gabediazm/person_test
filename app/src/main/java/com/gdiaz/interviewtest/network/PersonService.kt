package com.gdiaz.interviewtest.network

import com.gdiaz.interviewtest.models.PersonDetails
import com.gdiaz.interviewtest.models.Persons
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PersonService {

    @GET(PERSONS_LIST)
    suspend fun getIds(): Persons

    @GET(PERSON_DETAILS)
    suspend fun getPerson(@Path("id") id: String
    ): PersonDetails
}