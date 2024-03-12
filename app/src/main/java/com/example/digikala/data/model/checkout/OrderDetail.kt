package com.example.digikala.data.model.checkout

import com.example.digikala.data.model.basket.CartItem

data class OrderDetail(
    val token:String,
    val orderTotalPrice:Long,
    val orderTotalDiscount:Long,
    val orderAddress:String,
    val orderUserName:String,
    val orderUserPhone:String,
    val orderProducts: List<CartItem>,
)
//data class Products(
//    val name:String,
//    val seller:String,
//    val price:Long,
//    val discountPercent:Int,
//    val image:String,
//    val productId:String,
//    val count:Int,
//)