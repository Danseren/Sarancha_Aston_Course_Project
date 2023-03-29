package ru.aston.sarancha_aston_course_project.model.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterDto
import ru.aston.sarancha_aston_course_project.model.dto.episode.EpisodeDto
import ru.aston.sarancha_aston_course_project.model.dto.location.LocationDto

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

    @GET("episode")
    fun getFilteredEpisodeList(
        @Query("name") name: String,
        @Query("episode") episode: String
    ): Single<EpisodeDto>

    @Headers("Content-type: application/json")
    @GET("location")
    fun getLocationList(@Query("page") pageNumber: Int): Single<LocationDto>

    @GET("location")
    fun getFilteredLocationList(
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String
    ): Single<LocationDto>
}