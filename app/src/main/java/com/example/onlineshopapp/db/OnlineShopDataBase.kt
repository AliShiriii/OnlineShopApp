package com.example.onlineshopapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlineshopapp.db.dao.BasketDao
import com.example.onlineshopapp.db.dao.UserDao
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.models.UserEntity

@Database(entities = [UserEntity::class, BasketEntity::class], version = 3, exportSchema = false)
abstract class OnlineShopDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun basketDao(): BasketDao

    companion object {
        private var instance: OnlineShopDataBase? = null
        fun getInstance(context: Context): OnlineShopDataBase {

            if (instance == null) {

                instance =
                    Room.databaseBuilder(
                        context,
                        OnlineShopDataBase::class.java,
                        "onlineShop.db"
                    )
                        .fallbackToDestructiveMigration().build()
            }

            return instance as OnlineShopDataBase
        }

    }
}
