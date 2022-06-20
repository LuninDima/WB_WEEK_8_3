package com.example.wb_week_8_3.model.repository

import com.example.wb_week_8_3.model.data.CatModel
import com.example.wb_week_8_3.model.data.VoteModel


interface RemoteRepository {

  suspend fun getCatFromServer(): CatModel

  suspend fun getAllFavoriteCatsFromServer(): List<CatModel>

  suspend fun postLikeToServer(postRequest: VoteModel)


}