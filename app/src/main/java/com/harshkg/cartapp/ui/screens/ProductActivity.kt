package com.harshkg.cartapp.ui.screens
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.harshkg.cartapp.R
import com.harshkg.cartapp.databinding.ActivityProductBinding
import com.harshkg.cartapp.ui.adapter.ProductAdapter

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    lateinit var  viewmodel:VmProduct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.controller=this

        viewmodel = ViewModelProvider(this)[VmProduct::class.java]
        viewmodel.initializeProductList()

        observeViewModel()

        viewmodel.productList.observe(this) { productList ->
            binding.recyclerView.adapter = ProductAdapter(
                productList = productList,
                onAddToCartClick = { product -> viewmodel.addToCart(product)
                },
                onProductClick = {
                    product->
                    val intent = Intent(this, Product_Details::class.java)
                    intent.putExtra("product_data", product)
                    startActivity(intent)
                }
            )
        }
    }

    private fun observeViewModel() {
        viewmodel.cartItemCount.observe(this) { count ->
            binding.cartBanner.visibility = if (count > 0) View.VISIBLE else View.GONE
            val pluralSuffix = if (count != 1) "S" else ""
            binding.cartItemCountBanner.text = resources.getString(R.string.item_count, count, pluralSuffix)
        }

        viewmodel.totalPrice.observe(this) { total ->
            binding.cartTotalPriceLabel.text = getString(R.string.total_price, total)
        }

    }


    fun onViewCartClick() {
        val cartItems = ArrayList(viewmodel.cartItems.value ?: emptyList())
        val intent = Intent(this, ViewCart::class.java)
        intent.putParcelableArrayListExtra("cartItems", cartItems)
        startActivity(intent)
    }

    fun onCartViewClick(){

        startActivity(Intent(this,ViewCart::class.java))
    }

}


