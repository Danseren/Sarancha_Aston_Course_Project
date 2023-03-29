package ru.aston.sarancha_aston_course_project.model.dto.character

import com.google.gson.annotations.SerializedName
import ru.aston.sarancha_aston_course_project.model.dto.Location
import ru.aston.sarancha_aston_course_project.model.dto.Origin

data class CharacterInfo(
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String = "male",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String = "Name",
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("species")
    val species: String = "species",
    @SerializedName("status")
    val status: String = "Alive",
    @SerializedName("type")
    val type: String = "type",
    @SerializedName("url")
    val url: String
)