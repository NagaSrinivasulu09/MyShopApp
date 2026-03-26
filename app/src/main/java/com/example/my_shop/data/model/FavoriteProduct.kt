package com.example.my_shop.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteProduct(
    @PrimaryKey val id: String,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val userName: String
)
