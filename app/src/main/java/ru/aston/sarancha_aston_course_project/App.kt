package ru.aston.sarancha_aston_course_project

import android.app.Application
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.di.AppModule
import ru.aston.sarancha_aston_course_project.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var app: App
    }

    lateinit var appComponent: AppComponent
    val characterFilterData = CharacterFilterData()

    override fun onCreate() {
        super.onCreate()
        app = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}