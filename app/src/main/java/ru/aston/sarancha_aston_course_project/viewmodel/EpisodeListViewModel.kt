package ru.aston.sarancha_aston_course_project.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl

class EpisodeListViewModel() : ViewModel() {

    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitImpl()
    val episodeResult = controller.episodeResult

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

//    fun getFilterResult(characterFilter: CharacterFilterData) {
//        disposable = controller
//            .getFilteredData(characterFilter)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    episodeResult.postValue(it)
//                },
//                {
//
//                }
//            )
//    }

//    fun goToCharacterInfo(characterId: Int) {
//        disposable = controller
//            .getData(characterId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    episodeResult.postValue(it)
//                },
//                {
//
//                }
//            )
//    }

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
        super.onCleared()
    }
}
