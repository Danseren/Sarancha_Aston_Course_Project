package ru.aston.sarancha_aston_course_project.domain

data class LocationFilterData(
    var enabled: Boolean = false,
    var name: String = "",
    var type: String = "",
    var dimension: String = "",
)
