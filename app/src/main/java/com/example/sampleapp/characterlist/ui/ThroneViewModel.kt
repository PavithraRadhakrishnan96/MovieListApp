package com.example.sampleapp.characterlist.ui

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.sampleapp.characterlist.MyApplication
import com.example.sampleapp.characterlist.di.*
import com.example.sampleapp.characterlist.model.Characters
import com.example.sampleapp.characterlist.model.CharacterDao
import com.example.sampleapp.characterlist.model.CharacterDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ThroneViewModel : ViewModel() {

    @Inject
    lateinit var homeApi: APIInterface

  /*  @Inject
    lateinit var characterDao: CharacterDao*/

    private lateinit var disposable: Disposable
    private var characterLiveData = MutableLiveData<List<Characters>>()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        val injector: ViewModelInjector = DaggerViewModelInjector.builder()
            .networkModule(NetworkModule())
            .build()

        injector.inject(this)
        getPopularMovies()

    }

    private fun getPopularMovies() {
        disposable = homeApi.getCharacters().subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                   /* result.forEach {
                        insertRecord(it)
                    }*/
                    characterLiveData.postValue(result)
                },
                {
                    errorMessage.value = it.toString()
                }

            )

    }

    fun observeCharacterLiveData(): LiveData<List<Characters>> {
        return characterLiveData
    }

  /*  fun getAllRecords() {
        val list = characterDao.getAll()
        characterLiveData.value = list
    }

    fun insertRecord(characters: Characters) {
        characterDao.insert(characters)
    }

    fun getUserDao(characterDatabase: CharacterDatabase): CharacterDao {
        return characterDatabase.getCharacterDAO()
    }*/

}