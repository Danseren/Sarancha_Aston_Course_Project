package ru.aston.sarancha_aston_course_project.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.aston.sarancha_aston_course_project.database.CharactersDao
import ru.aston.sarancha_aston_course_project.database.CharactersDataBase
import ru.aston.sarancha_aston_course_project.utils.CHARACTERS_TABLE_NAME

@Module
class DataModule {

    @Provides
    fun provideDataBase(app: Application): CharactersDao {
        return Room.databaseBuilder(
            app,
            CharactersDataBase::class.java,
            CHARACTERS_TABLE_NAME
        ).build().getCharactersListDao()
    }
}