package ru.aston.sarancha_aston_course_project.model.dto.episode


import com.google.gson.annotations.SerializedName
import ru.aston.sarancha_aston_course_project.model.dto.Info

data class EpisodeDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<EpisodeInfo>
)