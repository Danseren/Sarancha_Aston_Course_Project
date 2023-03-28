package ru.aston.sarancha_aston_course_project

import android.app.Application
import android.content.Context
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.domain.CharacterFilterData
import ru.aston.sarancha_aston_course_project.utils.START_PAGE

class App : Application() {

    companion object {
        lateinit var app: App

        var pageNumber = START_PAGE
    }

    lateinit var appComponent: AppComponent
    val characterFilterData = CharacterFilterData()
    fun getContext(): Context = app.applicationContext

    override fun onCreate() {
        super.onCreate()
        app = this

    }
}