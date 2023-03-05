package ru.aston.sarancha_aston_course_project.viewmodel

import ru.aston.sarancha_aston_course_project.model.dto.RickAndMortyCharacter

sealed class AppState {
    data class Success (val character: RickAndMortyCharacter) : AppState()
    data class Error (val error : Throwable) : AppState()
    object Loading : AppState()
}