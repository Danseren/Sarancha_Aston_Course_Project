package ru.aston.sarancha_aston_course_project.database

class CharacterDataSource(val characterDao: CharactersDao) {

    fun getCharacters(): List<CharactersEntity> {
        return characterDao.getAllCharacters()
    }

    fun addCharacter(character: CharactersEntity) {
        characterDao.addCharacter(character)
    }

    fun addAllCharacters(charactersList: List<CharactersEntity>) {
        characterDao.addAllCharacters(charactersList)
    }
}