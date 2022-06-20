package com.example.wb_week_8_3.model.dataSource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FavoriteEntity")
    fun getAll(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(listEntity: List<FavoriteEntity>)

}
