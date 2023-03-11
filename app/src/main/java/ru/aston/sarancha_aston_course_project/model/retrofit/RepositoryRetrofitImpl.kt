package ru.aston.sarancha_aston_course_project.model.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.aston.sarancha_aston_course_project.model.dto.CharacterDto
import ru.aston.sarancha_aston_course_project.utils.RICK_AND_MORTY_API_RETROFIT

class RepositoryRetrofitImpl : Callback<CharacterDto> {

    val characterResult = MutableLiveData<CharacterDto>()

    fun getCharacterList() {

        val retrofitImpl = Retrofit
            .Builder()
            .baseUrl(RICK_AND_MORTY_API_RETROFIT)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
        val api = retrofitImpl.build().create(RickAndMortyAPI::class.java)
        val call: Call<CharacterDto> = api.getCharacterList()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<CharacterDto>, response: Response<CharacterDto>) {
        if (response.isSuccessful) {
            characterResult.postValue(response.body())
            Log.d("@@@", "${response.body()}")
        } else println(response.errorBody())
    }

    override fun onFailure(call: Call<CharacterDto>, t: Throwable) {
        t.printStackTrace()
    }
}