package ru.aston.sarancha_aston_course_project.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.aston.sarancha_aston_course_project.domain.LocationFilterData
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitLocationFilterImpl
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitLocationImpl

class LocationListViewModel() : ViewModel() {

    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitLocationImpl()
    val locationResult = controller.episodeResult

    private val locationFilterController = RepositoryRetrofitLocationFilterImpl()

    fun getResult(pageNumber: Int) {

        disposable = controller
            .getLocationData(pageNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    locationResult.postValue(it)
                },
                {

                }
            )
    }

    fun getFilterResult(episodeFilter: LocationFilterData) {
        disposable = locationFilterController
            .getFilteredData(episodeFilter)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    locationResult.postValue(it)
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
