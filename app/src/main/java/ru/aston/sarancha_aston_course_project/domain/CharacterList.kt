package ru.aston.sarancha_aston_course_project.domain

import ru.aston.sarancha_aston_course_project.model.dto.CharacterInfo

data class CharacterList(
    val pageNumber: Int,
    val characterList: List<CharacterInfo>
)
