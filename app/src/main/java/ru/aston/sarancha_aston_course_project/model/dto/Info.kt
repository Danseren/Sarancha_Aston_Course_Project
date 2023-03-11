package ru.aston.sarancha_aston_course_project.model.dto


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("prev")
    val prev: String?
)