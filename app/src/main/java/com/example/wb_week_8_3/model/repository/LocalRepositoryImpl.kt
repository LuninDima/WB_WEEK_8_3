package com.example.wb_week_8_3.model.repository

import com.example.wb_week_8_3.model.data.CatModel
import com.example.wb_week_8_3.model.dataSource.LocalDataSource

class LocalRepositoryImpl(private val localDataSource: LocalDataSource):LocalRepository {
    override suspend fun saveFavoriteCatToDB(catModel: CatModel){
        localDataSource.saveFavoriteCatToDB(catModel)
    }

    override suspend fun saveListFavoriteCatToDB(listCatModel: List<CatModel>) {
        return localDataSource.saveListFavoriteCatToDB(listCatModel)
    }

    override suspend fun getListCatsFromDB(): List<CatModel> {
        return localDataSource.getListCatsFromDB()
    }
}