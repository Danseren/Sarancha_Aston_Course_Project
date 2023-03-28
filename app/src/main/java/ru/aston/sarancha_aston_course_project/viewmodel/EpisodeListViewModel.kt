package ru.aston.sarancha_aston_course_project.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.aston.sarancha_aston_course_project.domain.CharacterFilterData
import ru.aston.sarancha_aston_course_project.domain.EpisodeFilterData
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitCharacterFilterImpl
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitEpisodeFilterImpl
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitEpisodeImpl

class EpisodeListViewModel() : ViewModel() {

    private var disposable: Disposable = Disposable.empty()

    private val controller = RepositoryRetrofitEpisodeImpl()
    val episodeResult = controller.episodeResult

    private val episodeFilterController = RepositoryRetrofitEpisodeFilterImpl()

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

    fun getFilterResult(episodeFilter: EpisodeFilterData) {
        disposable = episodeFilterController
            .getFilteredData(episodeFilter)
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
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
