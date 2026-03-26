package com.example.my_shop.presentation.components

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
import com.example.my_shop.presentation.viewmodel.FavoriteViewModel
import com.example.my_shop.presentation.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = viewModel()
) {

    val products = viewModel.productList
    val favorites by favoriteViewModel.favorites.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(products) { product ->

                val isFav = favorites.any { it.id == product.docId }

                ProductCard(
                    name = product.title,
                    price = product.price,
                    description = product.description,
                    imageUrl = product.image,
                    userName = product.userName,
                    isFavorite = isFav,
                    onClick = {
                        navController.navigate("details/${product.docId}")
                    },
                    onFavorite = {
                        favoriteViewModel.toggleFavorite(product)

                        scope.launch {
                            snackbarHostState.showSnackbar(
                                if (isFav)
                                    "Removed from favorites ❌"
                                else
                                    "Added to favorites ❤️"
                            )
                        }
                    }
                )
            }
        }
    }
}
