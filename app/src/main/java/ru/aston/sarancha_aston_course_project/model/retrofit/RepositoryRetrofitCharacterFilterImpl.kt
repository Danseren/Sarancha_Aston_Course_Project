package ru.aston.sarancha_aston_course_project.model.retrofit

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.aston.sarancha_aston_course_project.domain.CharacterFilterData
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterDto
import ru.aston.sarancha_aston_course_project.utils.RICK_AND_MORTY_API_RETROFIT

class RepositoryRetrofitCharacterFilterImpl {

    val result = MutableLiveData<CharacterDto>()

    private val retrofitImpl = Retrofit
        .Builder()
        .baseUrl(RICK_AND_MORTY_API_RETROFIT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    fun getFilteredData(characterFilter: CharacterFilterData): Single<CharacterDto> {
        return retrofitImpl
            .build()
            .create(RickAndMortyAPI::class.java)
            .getFilteredCharactersList(
                characterFilter.species,
                characterFilter.name,
                characterFilter.type,
                characterFilter.status,
                characterFilter.gender
            )
            .subscribeOn(Schedulers.io())
    }
}