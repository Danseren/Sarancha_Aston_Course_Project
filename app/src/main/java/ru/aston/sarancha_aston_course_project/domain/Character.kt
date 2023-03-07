package ru.aston.sarancha_aston_course_project.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character (
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val imgUrl: String
): Parcelable

fun getCharactersList() : List<Character> {
    return listOf(
        Character(1, "Rick Sanchez", "Human", "Alive", "Male", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
        Character(2, "Morty Smith", "Human", "Alive", "Male", "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
        Character(3, "Summer Smith", "Human", "Alive", "Female", "https://rickandmortyapi.com/api/character/avatar/3.jpeg"),
        Character(4, "Beth Smith", "Human", "Alive", "Female", "https://rickandmortyapi.com/api/character/avatar/4.jpeg"),
        Character(5, "Jerry Smith", "Human", "Alive", "Male", "https://rickandmortyapi.com/api/character/avatar/5.jpeg"),
        Character(6, "Abadango Cluster Princess", "Alien", "Alive", "Female", "https://rickandmortyapi.com/api/character/avatar/6.jpeg"),
        Character(7, "Abradolf Lincler", "Human", "unknown", "Male", "https://rickandmortyapi.com/api/character/avatar/7.jpeg"),
        Character(8, "Adjudicator Rick", "Human", "Dead", "Male", "https://rickandmortyapi.com/api/character/avatar/8.jpeg"),
    )
}