package com.gdiaz.interviewtest

import android.app.Application
import com.gdiaz.interviewtest.di.component.AppComponents
import com.gdiaz.interviewtest.di.component.DaggerAppComponents
import com.gdiaz.interviewtest.di.module.AppModule
import com.gdiaz.interviewtest.utils.InternetUtil

open class MainApplication : Application() {

    companion object {
        lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()
        appComponents = initDagger(this)
        InternetUtil.init(this)
    }

    private fun initDagger(app: MainApplication): AppComponents =
        DaggerAppComponents.builder()
            .appModule(AppModule(app))
            .build()

}