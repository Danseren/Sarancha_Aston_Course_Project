package ru.aston.sarancha_aston_course_project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.aston.sarancha_aston_course_project.utils.CHARACTERS_TABLE_NAME

@Entity(tableName = CHARACTERS_TABLE_NAME)
data class CharactersEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("page")
    val page: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("species")
    val species: String,
    @ColumnInfo("status")
    val status: String,
    @ColumnInfo("gender")
    val gender: String,
    @ColumnInfo("imgUrl")
    val imgUrl: String,
    @ColumnInfo("type")
    val type: String
)
