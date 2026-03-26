package com.example.my_shop.data.repository

import com.example.my_shop.data.local.room.FavoriteProductDao
import com.example.my_shop.data.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val dao : FavoriteProductDao){
    suspend fun addToFavorite(favoriteProduct: FavoriteProduct){
        dao.insert(favoriteProduct)
    }

    suspend fun removeFromFavorite(productId : String){
        dao.deleteById(productId)
    }

    fun getAllFavorites(): Flow<List<FavoriteProduct>>{
        return dao.getAllFavorites()
    }

    suspend fun isFavorite(id: String): Boolean{
        return dao.isFavorite(id)
    }
}