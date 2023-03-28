package ru.aston.sarancha_aston_course_project.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CharactersEntity::class
    ], version = 1
)

abstract class CharactersDataBase: RoomDatabase() {

    abstract fun getCharactersListDao() : CharactersDao
}