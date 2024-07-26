package io.github.mkutz.qac.approvaltesting

import java.time.LocalDate

data class ShopOrder(
    val id: String,
    val version: Int,
    val items: List<ShopItem>,
    val coupons: List<ShopCoupon> = emptyList(),
    val deliveryDate: LocalDate,
    val customer: ShopCustomer,
    val shippingAddress: ShopAddress,
    val billingAddress: ShopAddress
)

data class ShopItem(
    val id : String,
    val name : String,
    val amount: Int,
    val price: ShopPrice
)

data class ShopCoupon(
    val id : String,
    val description: String,
    val reducedRateInPercentage: Int
)

data class ShopPrice(
    val value : Int,
    val monetaryUnit: String,
    val currency: String
)

data class ShopCustomer(
    val id: String,
    val firstName: String,
    val lastName: String
)

data class ShopAddress(
    val id: String,
    val firstName: String,
    val lastName: String,
    val streetName: String,
    val houseNumber: String,
    val city: String,
    val country: String,
    val phone: String,
    val email: String,
    val postalCode: String
)
