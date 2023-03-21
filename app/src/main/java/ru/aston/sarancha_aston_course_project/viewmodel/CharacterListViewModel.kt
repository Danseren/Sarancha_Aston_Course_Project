package ru.aston.sarancha_aston_course_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl

class CharacterListViewModel() : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitImpl()
    val characterResult = controller.characterResult

    fun getLiveData(): MutableLiveData<AppState> {
        return liveData
    }

    fun getResult(pageNumber: Int) {

        disposable = controller
            .getData(pageNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    characterResult.postValue(it)
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