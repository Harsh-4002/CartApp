package com.harshkg.cartapp.ui.screens

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.harshkg.cartapp.R
import com.harshkg.cartapp.data.model.Product
import com.harshkg.cartapp.databinding.ActivityProductDetailsBinding

class Product_Details : AppCompatActivity() {
    private lateinit var binding:ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val product = intent.getParcelableExtra<Product>("product_data")

        product?.let {
            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.productImageView)

            binding.productNameTextView.text = it.name
            binding.productPriceTextView.text = it.price.toString()
           binding.productDetailsTextView.text =it.detail.toString()

        }
    }
}