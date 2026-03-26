package com.example.my_shop.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_shop.presentation.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(onLogout: () -> Unit) {

    val viewModel: AuthViewModel = viewModel()

    Column(modifier  = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            viewModel.signOut()
            onLogout()
        }) {
            Text("Logout")
        }
    }
}