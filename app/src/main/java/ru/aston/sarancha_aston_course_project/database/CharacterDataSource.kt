package ru.aston.sarancha_aston_course_project.database

import javax.inject.Inject

class CharacterDataSource
@Inject constructor(private val characterDao: CharactersDao) {

    fun getCharacters(): List<CharactersEntity> {
        return characterDao.getAllCharacters()
    }

    fun addAllCharacters(charactersList: List<CharactersEntity>) {
        characterDao.addAllCharacters(charactersList)
    }
}