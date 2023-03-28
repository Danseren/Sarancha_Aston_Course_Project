package ru.aston.sarancha_aston_course_project.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.aston.sarancha_aston_course_project.App
import ru.aston.sarancha_aston_course_project.database.CharactersDataBaseRepo
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.domain.CharacterFilterData
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterDto
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitCharacterFilterImpl
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl
import ru.aston.sarancha_aston_course_project.utils.Mapper
import javax.inject.Inject

class CharacterListViewModel() : ViewModel() {

    private var disposable: Disposable = Disposable.empty()

    private val characterController = RepositoryRetrofitImpl()
    val characterResult = characterController.result

    private val characterFilterController = RepositoryRetrofitCharacterFilterImpl()

    private val scope = CoroutineScope((Dispatchers.IO))

    @Inject
    lateinit var characterRepository: CharactersDataBaseRepo

    fun getResult(pageNumber: Int) {

        AppComponent.init(App.app).inject(this)

        disposable = characterController
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
            characterRepository.getCharLisFromNet(mapper.characterDtoToDataBase(characterDto))
            val characters =
                characterRepository.getCharLisFromNet(mapper.characterDtoToDataBase(characterDto))
            characterRepository.putAllCharactersToDataBase(characters)
            val charList = characterRepository.getCharactersFromDataBase()

            charList.forEach {
                Log.d("@@@", it.toString())
            }
        }
    }

    fun getFilterResult(characterFilter: CharacterFilterData) {
        disposable = characterFilterController
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

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
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
