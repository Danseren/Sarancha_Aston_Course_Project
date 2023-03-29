package ru.aston.sarancha_aston_course_project.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.aston.sarancha_aston_course_project.domain.EpisodeFilterData
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitEpisodeFilterImpl
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitEpisodeImpl

class EpisodeListViewModel() : ViewModel() {

    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitEpisodeImpl()
    val episodeResult = controller.episodeResult

    private val episodeFilterController = RepositoryRetrofitEpisodeFilterImpl()

    fun getResult(pageNumber: Int) {

        disposable = controller
            .getEpisodeData(pageNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    episodeResult.postValue(it)
                },
                {

                }
            )
    }

    fun getFilterResult(episodeFilter: EpisodeFilterData) {
        disposable = episodeFilterController
            .getFilteredData(episodeFilter)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    episodeResult.postValue(it)
                },
                {

                }
            )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
