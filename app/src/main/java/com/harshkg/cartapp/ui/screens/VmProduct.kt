package com.harshkg.cartapp.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harshkg.cartapp.data.model.Product

class VmProduct : ViewModel() {

    private val _cartList = MutableLiveData<MutableList<Product>>(mutableListOf())
    val cartList: LiveData<MutableList<Product>> = _cartList
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList
     val _cartItemCount = MutableLiveData<Int>(0)
    val cartItemCount: LiveData<Int> = _cartItemCount
   val _totalPrice = MutableLiveData<Double>(0.0)
    val totalPrice: LiveData<Double> = _totalPrice
    private val _cartItems = MutableLiveData<List<Product>>()
    val cartItems: LiveData<List<Product>> get() = _cartItems

    fun initializeProductList() {
        val products = listOf(
            Product(
                1,
                "Apple",
                50.0,
                "https://images.pexels.com/photos/1510392/pexels-photo-1510392.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                detail = "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting."
            ),
            Product(
                2,
                "Banana",
                20.0,
                "https://images.pexels.com/photos/61127/pexels-photo-61127.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                detail = "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting."
            ),
            Product(
                3,
                "Orange",
                30.0,
                "https://images.pexels.com/photos/1937743/pexels-photo-1937743.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                detail = "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting."
            ),
            Product(
                4,
                "Mango",
                60.0,
                "https://images.pexels.com/photos/2294471/pexels-photo-2294471.jpeg",
                "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting.",

            ),
            Product(
                5,
                "Grapes",
                40.0,
                "https://images.pexels.com/photos/60021/grapes-wine-fruit-vines-60021.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting."
            ),
            Product(
                6,
                "Carrots",
                22.0,
                "https://cdn.grofers.com/cdn-cgi/image/f=auto,fit=scale-down,q=85,metadata=none,w=120,h=120/app/assets/products/sliding_images/jpeg/63526075-fec6-42e9-a34c-086a069a0aac.jpg?ts=1711014093",
                "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting."
            ),
            Product(
                7,
                "Sweet Potato",
                25.0,
                "https://cdn.grofers.com/cdn-cgi/image/f=auto,fit=scale-down,q=85,metadata=none,w=120,h=120/app/assets/products/sliding_images/jpeg/185bc66d-57f1-4d60-818c-3ad4a52e09f3.jpg?ts=1732710610",
                "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting."
            ),
            Product(
                id = 8,
                name = "French Beans",
                price = 40.5,
                imageUrl = "https://cdn.grofers.com/cdn-cgi/image/f=auto,fit=scale-down,q=85,metadata=none,w=120,h=120/app/assets/products/large_images/jpeg/b983f690-172d-4e3f-afb7-f01e4a45158d.jpg?ts=1711473370",
                "Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. For commercial purposes, including botanical evaluation, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting."
            )
        )
        _productList.value = products
    }

    fun addToCart(product: Product) {
        val currentCart = _cartList.value ?: mutableListOf()
        if (!currentCart.contains(product)) {
            currentCart.add(product)
            _cartList.value = currentCart
            _cartItems.value = currentCart // Sync with _cartItems
            _cartItemCount.value = currentCart.size
            updateTotalPrice()
        }
    }


    private fun updateTotalPrice() {
        val total = _cartList.value?.sumOf { it.price } ?: 0.0
        _totalPrice.value = total
    }
}
