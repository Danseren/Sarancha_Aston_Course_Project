package ru.aston.sarancha_aston_course_project.model.retrofit

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.aston.sarancha_aston_course_project.domain.LocationFilterData
import ru.aston.sarancha_aston_course_project.model.dto.location.LocationDto
import ru.aston.sarancha_aston_course_project.utils.RICK_AND_MORTY_API_RETROFIT

class RepositoryRetrofitLocationFilterImpl {

    val result = MutableLiveData<LocationDto>()

    private val retrofitImpl = Retrofit
        .Builder()
        .baseUrl(RICK_AND_MORTY_API_RETROFIT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    fun getFilteredData(locationFilterData: LocationFilterData): Single<LocationDto> {
        return retrofitImpl
            .build()
            .create(RickAndMortyAPI::class.java)
            .getFilteredLocationList(
                locationFilterData.name,
                locationFilterData.type,
                locationFilterData.dimension
            )
            .subscribeOn(Schedulers.io())
    }
}