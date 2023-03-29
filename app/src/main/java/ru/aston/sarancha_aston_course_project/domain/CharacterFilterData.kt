package ru.aston.sarancha_aston_course_project.domain

data class CharacterFilterData(
    var enabled: Boolean = false,
    var species: String = "",
    var name: String = "",
    var type: String = "",
    var status: String = "alive",
    var gender: String = "female"
)
