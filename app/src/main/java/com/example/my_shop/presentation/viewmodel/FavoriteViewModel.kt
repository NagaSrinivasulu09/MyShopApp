package com.example.my_shop.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_shop.data.local.room.DatabaseProvider
import com.example.my_shop.data.model.FavoriteProduct
import com.example.my_shop.data.model.Product
import com.example.my_shop.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseProvider.getDatabase(application).favoriteProductDao()
    private val repository = FavoriteRepository(dao)

    val favorites = repository.getAllFavorites().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {

            val isFav = favorites.value.any { it.id == product.docId }

            if (isFav) {
                repository.removeFromFavorite(product.docId)
            } else {
                repository.addToFavorite(
                    FavoriteProduct(
                        id = product.docId,
                        title = product.title,
                        price = product.price,
                        description = product.description,
                        image = product.image,
                        userName = product.userName
                    )
                )
            }
        }
    }
}
