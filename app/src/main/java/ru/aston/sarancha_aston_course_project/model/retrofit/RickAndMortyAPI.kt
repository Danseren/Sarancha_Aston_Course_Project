package ru.aston.sarancha_aston_course_project.model.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterDto
import ru.aston.sarancha_aston_course_project.model.dto.episode.EpisodeDto

interface RickAndMortyAPI {

    @Headers("Content-type: application/json")
    @GET("character")
    fun getCharacterList(@Query("page") pageNumber: Int): Single<CharacterDto>

    @GET("character")
    fun getFilteredCharactersList(
        @Query("species") species: String,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("status") status: String,
        @Query("gender") gender: String
    ): Single<CharacterDto>

    @Headers("Content-type: application/json")
    @GET("episode")
    fun getEpisodeList(@Query("page") pageNumber: Int): Single<EpisodeDto>
}