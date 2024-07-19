package io.github.mkutz.qac.approvaltesting

import java.time.LocalDate
import java.time.LocalDateTime

data class Order(
    val id: String,
    val version: Int,
    val items: List<Item>,
    val coupons: List<Coupon>,
    val orderTimeStamp: LocalDateTime? = null,
    val deliveryDate: LocalDate,
    val shippingCost: List<Price> = emptyList(),
    val customer: Customer,
    val shippingAddress: Address,
    val billingAddress: Address
)

data class Item(
    val id : String,
    val name : String,
    val amount: Int,
    val price: Price
)

data class Coupon(
    val id : String,
    val description: String,
    val reducedRateInPercentage: Int
)

data class Price(
    val value : Int,
    val monetaryUnit: String,
    val currency: String
)

data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String
)

data class Address(
    val id: String,
    val firstName: String,
    val lastName: String,
    val streetName: String,
    val houseNumber: String,
    val city: String,
    val country: String,
    val phone: String,
    val latitude: String,
    val longitude: String,
    val email: String,
    val postalCode: String
)
