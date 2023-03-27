package ru.aston.sarancha_aston_course_project.di

import dagger.Module
import dagger.Provides
import ru.aston.sarancha_aston_course_project.navigation.IRouter
import ru.aston.sarancha_aston_course_project.navigation.Router

@Module
class RouterModule {

    @Provides
    fun router(): IRouter = Router()
}