package ru.aston.sarancha_aston_course_project.di

import dagger.Component
import ru.aston.sarancha_aston_course_project.MainActivity
import ru.aston.sarancha_aston_course_project.view.character.CharacterListFragment
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
    fun inject(characterListFragment: CharacterListFragment)
}