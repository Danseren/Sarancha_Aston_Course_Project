package ru.aston.sarancha_aston_course_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl

class CharacterListViewModel() : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitImpl()
    val characterResult = controller.characterResult

    fun getLiveData(): MutableLiveData<AppState> {
        return liveData
    }

    fun getResult() {

        disposable = Single
            .just(controller.getCharacterList())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}