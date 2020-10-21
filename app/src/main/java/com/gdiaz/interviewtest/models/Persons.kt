package com.gdiaz.interviewtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Persons(
    @SerializedName("status")
    val status: String,

    @SerializedName("data")
    val ids: ArrayList<String>,
) : Serializable