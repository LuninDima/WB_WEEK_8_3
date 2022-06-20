package com.example.wb_week_8_3.viewmodel

import com.example.wb_week_8_3.model.data.CatModel


sealed class AppStateCat {
    data class Success(val dataCat: CatModel) : AppStateCat()
    data class Error(val error: Throwable) : AppStateCat()
    object Loading : AppStateCat()
}

sealed class AppStateFavoriteCatsList {
    data class Success(val dataCat: List<CatModel>) : AppStateFavoriteCatsList()
    object Loading : AppStateFavoriteCatsList()
    data class Error(val error: Throwable) : AppStateFavoriteCatsList()
}
