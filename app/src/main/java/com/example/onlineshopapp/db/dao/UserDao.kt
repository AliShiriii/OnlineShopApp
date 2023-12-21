package com.example.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlineshopapp.db.models.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insert(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("select * from userEntity limit 1")
    fun get(): LiveData<UserEntity>

    @Query("select * from userEntity")
    fun deleteAll()
}