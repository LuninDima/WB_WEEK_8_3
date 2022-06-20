package com.example.wb_week_8_3.utils

import com.example.wb_week_8_3.model.data.CatModel
import com.example.wb_week_8_3.model.data.FavoriteModel
import com.example.wb_week_8_3.model.dataSource.room.FavoriteEntity


fun convertListEntityToListCatModel(listFavoriteEntity: List<FavoriteEntity>):List<CatModel>{
    return listFavoriteEntity.map { favoriteEntity ->
        CatModel(favoriteEntity.id, favoriteEntity.imageUrl)
    }
}

fun convertListCatoModelToListEntity(listCatModel: List<CatModel>):List<FavoriteEntity>{
    return listCatModel.map { catModel ->
        FavoriteEntity(catModel.id, catModel.url)

    }
}
fun convertCatModelToEntity(catModel: CatModel): FavoriteEntity {
    return FavoriteEntity(catModel.id, catModel.url)
}

fun convertFavoriteModelToCatModel(favoriteList: List<FavoriteModel>):List<CatModel>{
    return favoriteList.map { favoriteModel ->
        CatModel(favoriteModel.image.id, favoriteModel.image.url)
    }
}