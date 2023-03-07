package ru.aston.sarancha_aston_course_project.model

import ru.aston.sarancha_aston_course_project.domain.Character
import ru.aston.sarancha_aston_course_project.model.dto.CharacterInfo

interface RepositoryCharactersList {
    fun getList(): List<CharacterInfo>

    fun getLocalList(): List<Character>
}