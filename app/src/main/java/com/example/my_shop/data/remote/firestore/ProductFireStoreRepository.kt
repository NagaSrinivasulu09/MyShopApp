package com.example.my_shop.data.remote.firestore

import com.example.my_shop.data.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProductFirestoreRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun addProduct(product: Product, onComplete: (Boolean) -> Unit) {

        val userId = auth.currentUser?.uid ?: ""
        val userName = auth.currentUser?.email ?: ""

        val data = hashMapOf(
            "title" to product.title,
            "description" to product.description,
            "price" to product.price,
            "image" to product.image,
            "userName" to userName,
            "userId" to userId
        )

        db.collection("products")
            .add(data)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun getProducts(onResult: (List<Product>) -> Unit) {

        db.collection("products")
            .addSnapshotListener { result, error ->

                if (error != null) {
                    println("Firestore error: ${error.message}")
                    onResult(emptyList())
                    return@addSnapshotListener
                }

                val list = result?.map {
                    Product(
                        title = it.getString("title") ?: "",
                        description = it.getString("description") ?: "",
                        price = it.getDouble("price") ?: 0.0,
                        image = it.getString("image") ?: "",
                        userId = it.getString("userId") ?: "",
                        userName = it.getString("userName") ?: "",
                        docId = it.id
                    )
                } ?: emptyList()

                println("🔥 Live update: $list")

                onResult(list)
            }
    }
}