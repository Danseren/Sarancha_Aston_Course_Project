package ru.aston.sarancha_aston_course_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aston.sarancha_aston_course_project.model.Repository

class CharacterListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel() {
    lateinit var repository: Repository

    fun getLiveData() : MutableLiveData<AppState> {
        return liveData
    }

    fun sentRequest() {
        liveData.value = AppState.Loading
    }

    override fun onCleared() {
        super.onCleared()
    }
}