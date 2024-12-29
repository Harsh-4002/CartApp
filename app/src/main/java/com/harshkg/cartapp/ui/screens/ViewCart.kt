package com.harshkg.cartapp.ui.screens

import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.harshkg.cartapp.R

import com.harshkg.cartapp.data.model.Product
import com.harshkg.cartapp.databinding.ActivityViewCartBinding
import com.harshkg.cartapp.ui.adapter.CartAdapter

class ViewCart : AppCompatActivity() {
    private lateinit var binding: ActivityViewCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.controller=this
        val cartItems = intent.getParcelableArrayListExtra<Product>("cartItems") ?: emptyList()

        binding.cartTitle.text = "My Cart"
        setupRecyclerView(cartItems)
        calculateTotalPrice(cartItems)

    }

    private fun setupRecyclerView(cartItems: List<Product>) {
        binding.recyclerView.adapter = CartAdapter(cartItems)
        if (cartItems.size==0){
            binding.recyclerView.visibility= View.GONE
            binding.orderLayout.visibility=View.GONE
            binding.noDataFound.visibility=View.VISIBLE
        }

    }

    fun onPlaceOrderClick()
    {
        Toast.makeText(this, "Order Placed Successfully", Toast.LENGTH_SHORT).show()
    }
    private fun calculateTotalPrice(cartItems: List<Product>) {
        val totalPrice = cartItems.sumOf { it.price * (it.quantity ?: 1) }
        binding.totalPriceText.text = getString(R.string.total_price, totalPrice)
    }

}

