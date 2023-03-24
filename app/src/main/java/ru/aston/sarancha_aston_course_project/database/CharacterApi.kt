package ru.aston.sarancha_aston_course_project.database

class CharacterApi {

    fun getChar(): List<CharactersEntity> {
        return listCharacters
    }

    private val listCharacters = listOf(
        CharactersEntity(
            1,
            1,
            "Rick Sanchez",
            "Human",
            "Alive",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "1"
        ),
        CharactersEntity(
            2,
            3,
            "Summer Smith",
            "Human",
            "Alive",
            "Female",
            "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            "2"
        ),
        CharactersEntity(
            3,
            3,
            "Summer Smith",
            "Human",
            "Alive",
            "Female",
            "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            "3"
        ),
        CharactersEntity(
            4,
            4,
            "Beth Smith",
            "Human",
            "Alive",
            "Female",
            "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            "4"
        ),
        CharactersEntity(
            5,
            5,
            "Jerry Smith",
            "Human",
            "Alive",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            "5"
        ),
        CharactersEntity(
            6,
            6,
            "Abadango Cluster Princess",
            "Alien",
            "Alive",
            "Female",
            "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            "6"
        ),
        CharactersEntity(
            7,
            7,
            "Abradolf Lincler",
            "Human",
            "unknown",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/7.jpeg",
            "7"
        ),
        CharactersEntity(
            8,
            8,
            "Adjudicator Rick",
            "Human",
            "Dead",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
            "8"
        )
    )
}