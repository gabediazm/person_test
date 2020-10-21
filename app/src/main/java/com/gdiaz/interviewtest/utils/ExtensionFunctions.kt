package com.gdiaz.interviewtest.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(provider: ViewModelProvider.Factory) =
    ViewModelProvider(this, provider).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(this, provider).get(VM::class.java)


fun AppCompatActivity.loadFragment(fragment: Fragment, container: Int) {
    val className = fragment.javaClass.name
    supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, className)
        .commit()
}
