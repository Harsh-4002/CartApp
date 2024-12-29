package com.harshkg.cartapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshkg.cartapp.R
import com.harshkg.cartapp.data.model.Product
import com.harshkg.cartapp.databinding.ItemProductBinding
import com.harshkg.cartapp.ui.screens.ProductActivity

class ProductAdapter(
    private val productList: List<Product>,
    private val onAddToCartClick: (Product) -> Unit,
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            Glide.with(binding.productImage.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.productImage)

            binding.addToCartButton.setOnClickListener {
                onAddToCartClick(product)
                binding.addToCartButton.visibility = View.GONE
                binding.quantityLl.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                onProductClick(product)
            }

            binding.btnIncrease.setOnClickListener {
                product.quantity = product.quantity?.plus(1)
                notifyItemChanged(position)
                notifyCartUpdates()
            }

            binding.btnDecrease.setOnClickListener {
                if (product.quantity!! > 1) {
                    product.quantity = product.quantity?.minus(1)
                    notifyItemChanged(position)
                    notifyCartUpdates()
                }
            }
        }

        private fun notifyCartUpdates() {
            val updatedCartList = productList.filter { it.quantity!! > 0 }
            val totalItems = updatedCartList.sumOf { it.quantity!! }
            val totalPrice = updatedCartList.sumOf { it.price * it.quantity!! }

            notifyCartItemCount(totalItems)
            notifyTotalPrice(totalPrice)
        }

        private fun notifyCartItemCount(totalItems: Int) {
            (binding.root.context as? ProductActivity)?.apply {
                viewmodel._cartItemCount.value = totalItems
            }
        }

        private fun notifyTotalPrice(totalPrice: Double) {
            (binding.root.context as? ProductActivity)?.apply {
                viewmodel._totalPrice.value = totalPrice
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}

