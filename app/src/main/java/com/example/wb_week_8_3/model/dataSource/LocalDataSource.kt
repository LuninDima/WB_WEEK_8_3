package com.example.wb_week_8_3.model.dataSource

import com.example.wb_week_8_3.model.data.CatModel
import com.example.wb_week_8_3.model.dataSource.room.FavoriteDao
import com.example.wb_week_8_3.utils.convertCatModelToEntity
import com.example.wb_week_8_3.utils.convertListCatoModelToListEntity
import com.example.wb_week_8_3.utils.convertListEntityToListCatModel

class LocalDataSource(private val favoriteDataSource: FavoriteDao) {
    fun saveFavoriteCatToDB(catModel: CatModel){
        favoriteDataSource.insert(convertCatModelToEntity(catModel))
    }

    fun saveListFavoriteCatToDB(listCatModel: List<CatModel>){
        favoriteDataSource.insertAll(convertListCatoModelToListEntity(listCatModel))
    }

    fun getListCatsFromDB(): List<CatModel>{
       return convertListEntityToListCatModel(favoriteDataSource.getAll())
    }



}
