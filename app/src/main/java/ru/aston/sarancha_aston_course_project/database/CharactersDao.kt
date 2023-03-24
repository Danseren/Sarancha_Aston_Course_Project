package ru.aston.sarancha_aston_course_project.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.aston.sarancha_aston_course_project.utils.CHARACTERS_TABLE_NAME

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacter(charactersEntity: CharactersEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllCharacters(charactersList: List<CharactersEntity>)

    @Query("SELECT * FROM $CHARACTERS_TABLE_NAME")
    fun getAllCharacters(): List<CharactersEntity>
}