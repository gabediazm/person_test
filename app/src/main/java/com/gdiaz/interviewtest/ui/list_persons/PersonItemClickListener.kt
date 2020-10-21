package com.gdiaz.interviewtest.ui.list_persons

import com.gdiaz.interviewtest.models.Person

interface PersonItemClickListener {
    fun onPersonItemClicked(person: Person)
}
