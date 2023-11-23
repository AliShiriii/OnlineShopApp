package com.example.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.models.UserEntity

@Dao
interface BasketDao {

    @Insert
    fun add(basketEntity: BasketEntity)

    @Update
    fun update(basketEntity: BasketEntity)

    @Delete
    fun delete(basketEntity: BasketEntity)

    @Query("select * from Basketentity")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("select * from BasketEntity")
    fun deleteAll()

}