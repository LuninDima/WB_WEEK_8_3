package com.example.wb_week_8_3.model.repository

 import com.example.wb_week_8_3.model.data.CatModel
 import com.example.wb_week_8_3.model.data.VoteModel
 import com.example.wb_week_8_3.model.dataSource.RemoteDataSource

class RemoteRepositoryImpl(private val remoteDataSource: RemoteDataSource):
    RemoteRepository {


    override suspend fun getCatFromServer(): CatModel {
       return remoteDataSource.getRandomImageCat()
    }

    override suspend fun getAllFavoriteCatsFromServer(): List<CatModel> {
        return remoteDataSource.getAllFavoriteCatsFromServer()
    }

    override suspend fun postLikeToServer(postRequest: VoteModel){
        remoteDataSource.postLikeToServer(postRequest)
    }

}
