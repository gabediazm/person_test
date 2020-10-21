package com.gdiaz.interviewtest.ui.list_persons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gdiaz.interviewtest.R
import com.gdiaz.interviewtest.databinding.ItemPersonLayoutBinding
import com.gdiaz.interviewtest.models.Person
import com.gdiaz.interviewtest.models.PersonDetails

class PersonsAdapter(
    val personItemClickListener: PersonItemClickListener
) : RecyclerView.Adapter<PersonsAdapter.ViewHolder>() {

    var list: ArrayList<PersonDetails> = arrayListOf()

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_person_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(list[position].details)
    }

    inner class ViewHolder(private val viewDataBinding: ItemPersonLayoutBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bindViewHolder(person: Person) {
            viewDataBinding.person = person
            viewDataBinding.itemClickListener = personItemClickListener
        }
    }
}