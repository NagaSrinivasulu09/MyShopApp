package com.example.my_shop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.my_shop.presentation.viewmodel.AuthState
import com.example.my_shop.presentation.viewmodel.AuthViewModel

@Composable
fun SingUpScreen(navController: NavController, viewmodel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isMatch = password == confirmPassword
    val isError = confirmPassword.isNotEmpty() && !isMatch

    val authState = viewmodel.authState.observeAsState()

    val borderColor = when {
        isError -> Color.Red
        else -> Color(0xFF6C63FF)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 180.dp))
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
                text = "Create Your Account",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFFF5F5F5)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter your email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp
                ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF6C63FF),
                unfocusedBorderColor = Color.Gray,

                focusedLabelColor = Color(0xFF6C63FF),
                unfocusedLabelColor = Color.Gray,

                cursorColor = Color(0xFF6C63FF),

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Enter your password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp
                ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF6C63FF),
                unfocusedBorderColor = Color.Gray,

                focusedLabelColor = Color(0xFF6C63FF),
                unfocusedLabelColor = Color.Gray,

                cursorColor = Color(0xFF6C63FF),

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    if (isPasswordVisible) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = null,
                            tint = Color(0xFF6C63FF)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = Color(0xFF6C63FF)
                        )
                    }
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            label = { Text(text = "Confirm your password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp
                ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = Color.Gray,

                focusedLabelColor = Color(0xFF6C63FF),
                unfocusedLabelColor = Color.Gray,

                cursorColor = Color(0xFF6C63FF),

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    if (isPasswordVisible) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = null,
                            tint = Color(0xFF6C63FF)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = Color(0xFF6C63FF)
                        )
                    }
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewmodel.signUp(email, password)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp
                )
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0xFF6C63FF),
                            Color(0xFF00BCD4)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .shadow(elevation = 10.dp),
        ) {
            Text("Create", color = Color.White, style = MaterialTheme.typography.bodyLarge)
        }
        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text(
                text = "Already have an account?",
                color = Color(0xFF6C63FF),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        when (authState.value) {

            is AuthState.Loading -> {
                CircularProgressIndicator()
                Text("Creating account...")
            }

            is AuthState.Error -> {
                Text(
                    text = (authState.value as AuthState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> Unit
        }

        LaunchedEffect(authState.value) {
            if (authState.value is AuthState.Authenticate) {
                navController.navigate("login") {
                    popUpTo("signup") { inclusive = true }
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SingUpPreview() {
}