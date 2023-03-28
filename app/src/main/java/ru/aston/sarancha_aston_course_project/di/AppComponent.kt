package ru.aston.sarancha_aston_course_project.di

import dagger.Component
import ru.aston.sarancha_aston_course_project.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RouterModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
}