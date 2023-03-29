package ru.aston.sarancha_aston_course_project.model.dto.location

import com.google.gson.annotations.SerializedName
import ru.aston.sarancha_aston_course_project.model.dto.Info

data class LocationDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<LocationInfo>
)