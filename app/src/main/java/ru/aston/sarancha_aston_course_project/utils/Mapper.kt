package ru.aston.sarancha_aston_course_project.utils

import ru.aston.sarancha_aston_course_project.database.CharactersEntity
import ru.aston.sarancha_aston_course_project.model.dto.CharacterDto

class Mapper {

    fun characterDtoToDataBase(characterDto: CharacterDto): List<CharactersEntity> {
        val characterResult = mutableListOf<CharactersEntity>()

        for (character in characterDto.results) {
            val characterEntity = CharactersEntity(
                character.id.toLong(),
                character.id,
                character.name,
                character.species,
                character.status,
                character.gender,
                character.url,
                character.type
            )
        }

        return characterResult
    }
}