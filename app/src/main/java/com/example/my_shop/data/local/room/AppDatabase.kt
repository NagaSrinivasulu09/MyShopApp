package com.example.my_shop.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.my_shop.data.model.FavoriteProduct

@Database(entities = [FavoriteProduct::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun favoriteProductDao(): FavoriteProductDao
}
