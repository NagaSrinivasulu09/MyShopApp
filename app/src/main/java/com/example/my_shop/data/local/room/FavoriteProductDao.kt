package com.example.my_shop.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.my_shop.data.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {

    @Insert
    suspend fun insert(favoriteProduct: FavoriteProduct)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteProduct>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean
}
