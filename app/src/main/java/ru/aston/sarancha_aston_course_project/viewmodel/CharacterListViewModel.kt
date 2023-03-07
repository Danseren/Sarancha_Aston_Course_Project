package ru.aston.sarancha_aston_course_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aston.sarancha_aston_course_project.model.RepositoryCharactersList
import ru.aston.sarancha_aston_course_project.model.RepositoryCharactersListImpl

class CharacterListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel() {
    lateinit var repositoryCharactersList: RepositoryCharactersList

    fun getLiveData() : MutableLiveData<AppState> {
        repositoryCharactersList = RepositoryCharactersListImpl()
        return liveData
    }

    fun sentRequest() {
        liveData.value = AppState.Loading
        liveData.postValue(
//            AppState.Success(repositoryCharactersList.getList())
            AppState.Success(repositoryCharactersList.getLocalList())
        )
    }

    override fun onCleared() {
        super.onCleared()
    }
}