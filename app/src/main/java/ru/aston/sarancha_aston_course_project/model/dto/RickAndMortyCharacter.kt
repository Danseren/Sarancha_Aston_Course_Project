package ru.aston.sarancha_aston_course_project.model.dto


import com.google.gson.annotations.SerializedName

data class RickAndMortyCharacter(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)