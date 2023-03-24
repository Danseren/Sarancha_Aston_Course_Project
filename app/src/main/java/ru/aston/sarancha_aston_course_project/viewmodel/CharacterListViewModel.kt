package ru.aston.sarancha_aston_course_project.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.aston.sarancha_aston_course_project.App
import ru.aston.sarancha_aston_course_project.CharacterFilterData
import ru.aston.sarancha_aston_course_project.database.CharacterDataSource
import ru.aston.sarancha_aston_course_project.database.CharactersDataBaseRepo
import ru.aston.sarancha_aston_course_project.model.dto.CharacterDto
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl
import ru.aston.sarancha_aston_course_project.utils.Mapper

class CharacterListViewModel() : ViewModel() {

    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitImpl()
    val characterResult = controller.characterResult

    private val scope = CoroutineScope((Dispatchers.IO))

    val repository by lazy {
        val characterDao = App.dataBase.getCharactersListDao()
        val characterDataSource = CharacterDataSource(characterDao)
        CharactersDataBaseRepo(characterDataSource = characterDataSource)
    }

    fun getResult(pageNumber: Int) {

        disposable = controller
            .getData(pageNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    characterResult.postValue(it)

                    saveToDatabase(it)
                },
                {

                }
            )
    }

    fun saveToDatabase(characterDto: CharacterDto) {

        val mapper: Mapper

        scope.launch {
            repository.getCharactersFromRemote()
            val characters = repository.getCharactersFromRemote()
            repository.putAllCharactersToDataBase(characters)
            val charList = repository.getCharactersFromDataBase()

            charList.forEach {
                Log.d("@@@", it.toString())
            }
        }
    }

    fun getFilterResult(characterFilter: CharacterFilterData) {
        disposable = controller
            .getFilteredData(characterFilter)
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
        scope.cancel()
        super.onCleared()
    }
}
