package ru.aston.sarancha_aston_course_project.database

class CharactersDataBaseRepo(
    private val characterApi: CharacterApi = CharacterApi(),
    private val characterDataSource: CharacterDataSource
) {

    fun getCharactersFromDataBase(): List<CharactersEntity> {
        return characterDataSource.getCharacters()
    }

    fun putCharacterToDataBase(character: CharactersEntity) {
        characterDataSource.addCharacter(character)
    }

    fun putAllCharactersToDataBase(charactersList: List<CharactersEntity>) {
        characterDataSource.addAllCharacters(charactersList)
    }

    fun getCharactersFromRemote(): List<CharactersEntity> {
        return characterApi.getChar()
    }
}