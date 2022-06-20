package com.example.wb_week_8_3.app

import android.app.Application
import androidx.room.Room
import com.example.wb_week_8_3.model.dataSource.room.FavoriteDao
import com.example.wb_week_8_3.model.dataSource.room.FavoriteDataBase
import com.github.terrakok.cicerone.Cicerone

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }



    companion object{
        private var appInstance: App? = null
        private var db: FavoriteDataBase? = null
        private const val DB_NAME = "FAVORITE.db"

        fun getFavoriteDao(): FavoriteDao{
            if(db == null){
                synchronized(FavoriteDataBase::class.java){
                    if(db == null){
                        if(appInstance == null) throw
                                IllegalAccessException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext, FavoriteDataBase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.favoriteDao()
        }
    }
}

