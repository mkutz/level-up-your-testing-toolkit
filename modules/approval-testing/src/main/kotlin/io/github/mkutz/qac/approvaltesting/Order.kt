package io.github.mkutz.qac.approvaltesting

import java.time.Instant
import java.time.LocalDate

data class Order(
    val id: String,
    val version: Int,
    val items: List<Item>,
    val coupons: List<Coupon>,
    val orderTimeStamp: Instant,
    val deliveryDate: LocalDate,
    val shippingCost: List<Cost>,
    val customer: Customer
)

data class Item(
    val id : String
)

data class Coupon(
    val id : String
)

data class Cost(
    val value : Int,
    val currency: String
)

data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String
)

data class Address(
    val id : String
)
