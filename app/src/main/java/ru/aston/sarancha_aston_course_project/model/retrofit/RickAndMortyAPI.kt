package ru.aston.sarancha_aston_course_project.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.aston.sarancha_aston_course_project.model.dto.CharacterDto

interface RickAndMortyAPI {

    @Headers("Content-type: application/json")
    @GET("character")
    fun getCharacterList(): Call<CharacterDto>
}