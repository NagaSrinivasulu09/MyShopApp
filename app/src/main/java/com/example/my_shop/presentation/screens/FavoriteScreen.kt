package com.example.my_shop.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.my_shop.data.model.Product
import com.example.my_shop.presentation.components.ProductCard
import com.example.my_shop.presentation.viewmodel.FavoriteViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    viewModel: FavoriteViewModel = viewModel(),
    navController: NavController
) {

    val favorites by viewModel.favorites.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {

            items(favorites) { product ->

                val isFav = true // already favorite screen

                ProductCard(
                    name = product.title,
                    price = product.price,
                    description = product.description,
                    imageUrl = product.image,
                    userName = product.userName,
                    isFavorite = isFav,

                    onClick = {
                        navController.navigate("details/${product.id}")
                    },

                    onFavorite = {
                        viewModel.toggleFavorite(
                            Product(
                                title = product.title,
                                price = product.price,
                                description = product.description,
                                image = product.image,
                                docId = product.id,
                                userName = product.userName
                            )
                        )

                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Removed from favorites ❌"
                            )
                        }
                    }
                )
            }
        }
    }
}