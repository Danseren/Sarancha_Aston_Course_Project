package ru.aston.sarancha_aston_course_project.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val imgUrl: String
) : Parcelable