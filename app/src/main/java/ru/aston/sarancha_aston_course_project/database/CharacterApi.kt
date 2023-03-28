package ru.aston.sarancha_aston_course_project.database

import javax.inject.Inject

class CharacterApi
@Inject constructor() {

    fun getCharFromNet(charList: List<CharactersEntity>): List<CharactersEntity> {
        return charList
    }
}