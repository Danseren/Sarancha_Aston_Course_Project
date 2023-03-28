package ru.aston.sarancha_aston_course_project.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.aston.sarancha_aston_course_project.MainActivity
import ru.aston.sarancha_aston_course_project.view.character.CharacterListFragment
import ru.aston.sarancha_aston_course_project.view.episode.EpisodesListFragment
import ru.aston.sarancha_aston_course_project.viewmodel.CharacterListViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RouterModule::class,
        DataModule::class
    ]
)

interface AppComponent {

    companion object {
        fun init(app: Application): AppComponent {
            return DaggerAppComponent.factory().create(app)
        }
    }

    fun inject(mainActivity: MainActivity)
    fun inject(characterListFragment: CharacterListFragment)
    fun inject(episodesListFragment: EpisodesListFragment)
    fun inject(characterListViewModel: CharacterListViewModel)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}