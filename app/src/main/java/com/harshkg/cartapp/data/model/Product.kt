package com.harshkg.cartapp.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val detail:String?,
    var quantity: Int? = 0
) : Parcelable

