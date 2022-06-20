package com.example.wb_week_8_3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wb_week_8_3.app.App.Companion.getFavoriteDao
import com.example.wb_week_8_3.model.data.CatModel
import com.example.wb_week_8_3.model.data.VoteModel
import com.example.wb_week_8_3.model.dataSource.LocalDataSource
import com.example.wb_week_8_3.model.dataSource.RemoteDataSource
import com.example.wb_week_8_3.model.repository.LocalRepository
import com.example.wb_week_8_3.model.repository.LocalRepositoryImpl
import com.example.wb_week_8_3.model.repository.RemoteRepository
import com.example.wb_week_8_3.model.repository.RemoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppStateCat> = MutableLiveData(),
    private val remoteRepository: RemoteRepository = RemoteRepositoryImpl(RemoteDataSource()),
    private val localRepository: LocalRepository = LocalRepositoryImpl(
        LocalDataSource(getFavoriteDao())
    )
) : ViewModel() {
    fun getLiveData() = liveDataObserver
    fun getData() {
        AppStateCat.Loading
        viewModelScope.launch(Dispatchers.IO) {
            var listCats = localRepository.getListCatsFromDB()
            if (listCats.isEmpty()) {
                listCats = remoteRepository.getAllFavoriteCatsFromServer()
                localRepository.saveListFavoriteCatToDB(listCats)
            }
        }
        getCatFromRemoteSource()
    }

    fun postLikeToServer(postRequest: VoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.postLikeToServer(postRequest)
        }
    }

    fun saveFavoriteCatToDB(catModel: CatModel) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.saveFavoriteCatToDB(catModel)
        }
    }

    fun getCatFromRemoteSource() {
        liveDataObserver.value = AppStateCat.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = remoteRepository.getCatFromServer()
            if (response.id == "" || response.url == "") {
                liveDataObserver.postValue(AppStateCat.Error(Throwable()))
            } else liveDataObserver.postValue(
                AppStateCat.Success(
                    response
                )
            )
        }
    }
}