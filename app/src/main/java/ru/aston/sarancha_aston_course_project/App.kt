package ru.aston.sarancha_aston_course_project

import android.app.Application

class App : Application() {

    companion object {
        lateinit var app: App
    }

    lateinit var router: Router

    override fun onCreate() {
        super.onCreate()
        app = this
        router = Router()
    }
}