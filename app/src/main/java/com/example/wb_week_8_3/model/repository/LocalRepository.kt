package com.example.wb_week_8_3.model.repository

import com.example.wb_week_8_3.model.data.CatModel

interface LocalRepository {
    suspend fun saveFavoriteCatToDB(catModel: CatModel)

    suspend fun saveListFavoriteCatToDB(listCatModel: List<CatModel>)

    suspend fun getListCatsFromDB(): List<CatModel>
}