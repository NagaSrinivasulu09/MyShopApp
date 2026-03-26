package com.example.my_shop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.my_shop.presentation.components.ProductListScreen
import com.example.my_shop.presentation.viewmodel.ProductViewModel

@Composable
fun HomeScreen(navController: NavController) {

    val productViewModel: ProductViewModel = viewModel()

    LaunchedEffect(Unit) {
        productViewModel.fetchProductsFromFirestore()
    }

    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_product")
            }) {
                Text("+")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {

            ProductListScreen(
                navController = navController,
                viewModel = productViewModel
            )
        }
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6C63FF),
                        Color(0xFF00BCD4)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            "Discover. Shop. Enjoy.",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview
@Composable
private fun TopBarPreview() {
    TopBar()
}