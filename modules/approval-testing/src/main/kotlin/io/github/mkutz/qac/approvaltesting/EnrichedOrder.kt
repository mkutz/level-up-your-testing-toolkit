package io.github.mkutz.qac.approvaltesting

import java.time.LocalDate
import java.time.LocalDateTime

data class EnrichedOrder(
    val id: String,
    val version: Int,
    val items: List<EnrichedItem>,
    val coupons: List<EnrichedCoupon> = emptyList(),
    val orderTimeStamp: LocalDateTime,
    val deliveryDate: LocalDate,
    val shippingCost: List<EnrichedPrice>,
    val customer: EnrichedCustomer,
    val shippingAddress: EnrichedAddress,
    val billingAddress: EnrichedAddress
)

data class EnrichedItem(
    val id: String,
    val name: String,
    val amount: Int,
    val price: EnrichedPrice
)

data class EnrichedCoupon(
    val id: String,
    val description: String,
    val reducedRateInPercentage: Int
)

data class EnrichedPrice(
    val value: Int,
    val monetaryUnit: String,
    val currency: String
)

data class EnrichedCustomer(
    val id: String,
    val firstName: String,
    val lastName: String
)

data class EnrichedAddress(
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
