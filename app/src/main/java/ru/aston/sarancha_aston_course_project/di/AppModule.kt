package ru.aston.sarancha_aston_course_project.di

import dagger.Module
import dagger.Provides
import ru.aston.sarancha_aston_course_project.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app
}