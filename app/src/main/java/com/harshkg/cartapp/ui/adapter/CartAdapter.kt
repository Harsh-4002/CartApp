package com.harshkg.cartapp.ui.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshkg.cartapp.R

import com.harshkg.cartapp.data.model.Product
import com.harshkg.cartapp.databinding.ItemCartBinding

class CartAdapter(private val cartItems: List<Product>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.binding.product = product
        Glide.with(holder.binding.productImage.context)
            .load(product.imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.productImage)

        holder.binding.btnIncrease.setOnClickListener {
            product.quantity = product.quantity?.plus(1)
            notifyItemChanged(position)
        }

        holder.binding.btnDecrease.setOnClickListener {
            if (product.quantity!! > 1) {
                product.quantity = product.quantity!! - 1
                notifyItemChanged(position)

            }
        }

        holder.binding.executePendingBindings()
    }




    override fun getItemCount(): Int = cartItems.size
}

