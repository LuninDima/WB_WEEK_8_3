package com.example.wb_week_8_3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wb_week_8_3.app.App.Companion.getFavoriteDao
import com.example.wb_week_8_3.model.dataSource.LocalDataSource
import com.example.wb_week_8_3.model.repository.LocalRepository
import com.example.wb_week_8_3.model.repository.LocalRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    val favoriteLiveData: MutableLiveData<AppStateFavoriteCatsList> = MutableLiveData(),
    private val localRepository: LocalRepository = LocalRepositoryImpl(
        LocalDataSource(
            getFavoriteDao()
        )
    )
) : ViewModel() {

    fun getAllFavoriteCats() {
        favoriteLiveData.value = AppStateFavoriteCatsList.Loading
        viewModelScope.launch(Dispatchers.IO) {
            favoriteLiveData.postValue(
                AppStateFavoriteCatsList.Success(localRepository.getListCatsFromDB())
            )
        }
    }
}
