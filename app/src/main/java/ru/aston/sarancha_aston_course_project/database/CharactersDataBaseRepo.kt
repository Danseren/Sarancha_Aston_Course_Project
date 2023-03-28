package ru.aston.sarancha_aston_course_project.database

class CharactersDataBaseRepo(
    private val characterApi: CharacterApi = CharacterApi(),
    private val characterDataSource: CharacterDataSource
) {

    fun getCharactersFromDataBase(): List<CharactersEntity> {
        return characterDataSource.getCharacters()
    }

    fun putAllCharactersToDataBase(charactersList: List<CharactersEntity>) {
        characterDataSource.addAllCharacters(charactersList)
    }

    fun getCharLisFromNet(list: List<CharactersEntity>): List<CharactersEntity> {
        return characterApi.getCharFromNet(list)
    }
}