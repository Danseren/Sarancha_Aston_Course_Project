package ru.aston.sarancha_aston_course_project.model.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.aston.sarancha_aston_course_project.model.dto.CharacterDto

interface RickAndMortyAPI {

    @Headers("Content-type: application/json")
    @GET("character")
    fun getCharacterList(@Query("page") pageNumber: Int): Single<CharacterDto>
}