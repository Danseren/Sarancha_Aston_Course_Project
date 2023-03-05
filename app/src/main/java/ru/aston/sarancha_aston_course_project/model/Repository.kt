package ru.aston.sarancha_aston_course_project.model

import ru.aston.sarancha_aston_course_project.model.dto.RickAndMortyCharacter

interface Repository {
    fun getList(): List<RickAndMortyCharacter>
}