package ru.aston.sarancha_aston_course_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl

class CharacterListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel() {

    private val controller = RepositoryRetrofitImpl()
    val characterResult = controller.characterResult

    fun getLiveData(): MutableLiveData<AppState> {
        return liveData
    }

    fun getResult() {
        controller.getCharacterList()
    }

    override fun onCleared() {
        super.onCleared()
    }
}