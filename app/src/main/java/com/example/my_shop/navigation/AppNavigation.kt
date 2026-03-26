package com.example.my_shop.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_shop.presentation.screens.LoginScreen
import com.example.my_shop.presentation.screens.SingUpScreen
import com.example.my_shop.presentation.viewmodel.AuthState
import com.example.my_shop.presentation.viewmodel.AuthViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticate -> {
                navController.navigate("main") {
                    popUpTo(0) { inclusive = true }
                }
            }

            is AuthState.UnAuthenticate -> {
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            }

            else -> Unit
        }
    }

    NavHost(navController = navController, startDestination = "loading") {
        composable("loading") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        composable("login") {
            LoginScreen(navController = navController, viewmodel = authViewModel)
        }
        composable("signup") {
            SingUpScreen(navController = navController, viewmodel = authViewModel)
        }
        composable("main") {
            MainScreen(authViewModel)
        }
    }
}