package ru.aston.sarancha_aston_course_project.model

import ru.aston.sarancha_aston_course_project.domain.getCharactersList
import ru.aston.sarancha_aston_course_project.model.dto.CharacterInfo
import ru.aston.sarancha_aston_course_project.domain.Character

class RepositoryCharactersListImpl: RepositoryCharactersList {
    override fun getList(): List<CharacterInfo> {
        //Nothing to do
        return listOf()
    }

    override fun getLocalList(): List<Character> {
        return getCharactersList()
    }
}