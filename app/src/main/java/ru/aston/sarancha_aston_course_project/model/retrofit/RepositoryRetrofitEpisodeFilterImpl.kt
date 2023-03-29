package ru.aston.sarancha_aston_course_project.model.retrofit

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.aston.sarancha_aston_course_project.domain.EpisodeFilterData
import ru.aston.sarancha_aston_course_project.model.dto.episode.EpisodeDto
import ru.aston.sarancha_aston_course_project.utils.RICK_AND_MORTY_API_RETROFIT

class RepositoryRetrofitEpisodeFilterImpl {

    val result = MutableLiveData<EpisodeDto>()

    private val retrofitImpl = Retrofit
        .Builder()
        .baseUrl(RICK_AND_MORTY_API_RETROFIT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    fun getFilteredData(episodeFilter: EpisodeFilterData): Single<EpisodeDto> {
        return retrofitImpl
            .build()
            .create(RickAndMortyAPI::class.java)
            .getFilteredEpisodeList(
                episodeFilter.name,
                episodeFilter.episode
            )
            .subscribeOn(Schedulers.io())
    }
}