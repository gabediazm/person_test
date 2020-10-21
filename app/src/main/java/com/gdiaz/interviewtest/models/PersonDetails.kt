package com.gdiaz.interviewtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PersonDetails(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val details: Person,
) : Serializable

data class Person(
    @SerializedName("id")
    val id: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("age")
    val age: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("country")
    val country: String,
) : Serializable