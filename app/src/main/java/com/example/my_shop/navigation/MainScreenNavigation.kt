package com.example.my_shop.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_shop.presentation.components.BottomBar
import com.example.my_shop.presentation.screens.AddProductScreen
import com.example.my_shop.presentation.screens.DetailsScreen
import com.example.my_shop.presentation.screens.FavoritesScreen
import com.example.my_shop.presentation.screens.HomeScreen
import com.example.my_shop.presentation.screens.ProfileScreen
import com.example.my_shop.presentation.viewmodel.AuthViewModel
import com.example.my_shop.presentation.viewmodel.ProductViewModel

@Composable
fun MainScreen(authViewModel: AuthViewModel) {

    val navController = rememberNavController()
    val productViewModel: ProductViewModel = viewModel()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {

            composable("home") {
                HomeScreen(navController)
            }

            composable("favorites") {
                FavoritesScreen(navController = navController)
            }

            composable("profile") {
                ProfileScreen(
                    onLogout = {
                        authViewModel.signOut()
                    }
                )
            }
            composable("details/{docId}") { backStackEntry ->
                val docId = backStackEntry.arguments?.getString("docId") ?: ""

                DetailsScreen(docId = docId)
            }
            composable("add_product") {
                AddProductScreen(
                    viewModel = productViewModel,
                    navController = navController
                )
            }
        }
    }
}
