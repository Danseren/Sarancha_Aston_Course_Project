package ru.aston.sarancha_aston_course_project.model.dto

import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)