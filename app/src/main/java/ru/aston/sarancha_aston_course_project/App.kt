package ru.aston.sarancha_aston_course_project

import android.app.Application
import android.content.Context
import androidx.room.Room
import ru.aston.sarancha_aston_course_project.database.CharactersDataBase
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.di.AppModule
import ru.aston.sarancha_aston_course_project.di.DaggerAppComponent
import ru.aston.sarancha_aston_course_project.domain.CharacterFilterData
import ru.aston.sarancha_aston_course_project.utils.CHARACTERS_TABLE_NAME
import ru.aston.sarancha_aston_course_project.utils.START_PAGE

class App : Application() {

    companion object {
        lateinit var app: App

        lateinit var dataBase: CharactersDataBase
        var pageNumber = START_PAGE
    }

    lateinit var appComponent: AppComponent
    val characterFilterData = CharacterFilterData()
    fun getContext(): Context = app.applicationContext

    override fun onCreate() {
        super.onCreate()
        app = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        dataBase = Room.databaseBuilder(
            this,
            CharactersDataBase::class.java,
            CHARACTERS_TABLE_NAME
        ).build()
    }
}