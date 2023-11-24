package com.example.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlineshopapp.db.models.BasketEntity

@Dao
interface BasketDao {

    @Insert
    fun insert(basketEntity: BasketEntity)

    @Update
    fun update(basketEntity: BasketEntity)

    @Delete
    fun delete(basketEntity: BasketEntity)

    @Query("select * from Basketentity")
    fun getAll(): LiveData<List<BasketEntity>>

    @Query("select * from BasketEntity")
    fun deleteAll()

}