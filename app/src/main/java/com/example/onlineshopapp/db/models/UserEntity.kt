package com.example.onlineshopapp.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var customerId: Long,
    var firstName: String?,
    var lastName: String?,
    var address: String?,
    var phone: String?,
    var postalCode: String?,
    var token: String?,
    var username: String?,
    var userId: Long,
)