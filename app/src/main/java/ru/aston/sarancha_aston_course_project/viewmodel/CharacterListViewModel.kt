package ru.aston.sarancha_aston_course_project.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.aston.sarancha_aston_course_project.App
import ru.aston.sarancha_aston_course_project.domain.CharacterFilterData
import ru.aston.sarancha_aston_course_project.database.CharacterDataSource
import ru.aston.sarancha_aston_course_project.database.CharactersDataBaseRepo
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterDto
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl
import ru.aston.sarancha_aston_course_project.utils.Mapper

class CharacterListViewModel() : ViewModel() {

    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitImpl()
    val characterResult = controller.result

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

        val mapper = Mapper()

        scope.launch {
            repository.getCharLisFromNet(mapper.characterDtoToDataBase(characterDto))
            val characters = repository.getCharLisFromNet(mapper.characterDtoToDataBase(characterDto))
            repository.putAllCharactersToDataBase(characters)
            val charList = repository.getCharactersFromDataBase()

//            charList.forEach {
//                Log.d("@@@", it.toString())
//            }
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

    fun goToCharacterInfo(characterId: Int) {
        disposable = controller
            .getData(characterId)
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

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->return true
            }
        }
        return false
    }

    override fun onCleared() {
        disposable.dispose()
        scope.cancel()
        super.onCleared()
    }
}
