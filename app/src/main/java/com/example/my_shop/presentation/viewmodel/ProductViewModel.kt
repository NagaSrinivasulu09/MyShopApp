package com.example.my_shop.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_shop.data.model.Product
import com.example.my_shop.data.remote.cloudinary.CloudinaryRepository
import com.example.my_shop.data.remote.firestore.ProductFirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val cloudinaryRepository = CloudinaryRepository()
    private val firestoreRepository = ProductFirestoreRepository()

    var productList by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        fetchProductsFromFirestore()
    }

    fun uploadProduct(
        context: Context,
        name: String,
        description: String,
        price: Double,
        imageUri: Uri
    ) {
        viewModelScope.launch {

            isLoading = true
            println("🚀 Upload started")

            try {
                val imageUrl = cloudinaryRepository.uploadImage(context, imageUri)

                println("📸 Image URL: $imageUrl")

                if (imageUrl != null) {

                    val product = Product(
                        title = name,
                        description = description,
                        price = price,
                        image = imageUrl
                    )

                    println("📦 Saving to Firestore...")

                    firestoreRepository.addProduct(product) { success ->

                        if (success) {
                            println("✅ Product uploaded successfully")
                            fetchProductsFromFirestore()
                        } else {
                            println("❌ Firestore failed")
                        }
                    }

                } else {
                    println("❌ Image upload failed")
                }

            } catch (e: Exception) {
                println("🔥 Exception: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun fetchProductsFromFirestore() {
        firestoreRepository.getProducts { list ->
            println("Fetched Products: $list")
            productList = list
        }
    }
}