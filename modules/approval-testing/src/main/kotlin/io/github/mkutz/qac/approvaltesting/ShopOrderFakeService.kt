package io.github.mkutz.qac.approvaltesting

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun ShopAddress.enrich(latitude: String, longitude: String) = EnrichedAddress(
    id = id,
    firstName = firstName,
    lastName = lastName,
    streetName = streetName,
    houseNumber = houseNumber,
    city = city,
    country = country,
    phone = phone,
    latitude = latitude,
    longitude = longitude,
    email = email,
    postalCode = postalCode
)

fun ShopCustomer.enrich() = EnrichedCustomer(
    id = id,
    firstName = firstName,
    lastName = lastName,
)

fun ShopPrice.enrich() = EnrichedPrice(
    value = value,
    monetaryUnit = monetaryUnit,
    currency = currency
)

fun ShopItem.enrich() = EnrichedItem(
    id = id,
    name = name,
    amount = amount,
    price = price.enrich(),
)

fun ShopCoupon.enrich() = EnrichedCoupon(
    id = id,
    description = description,
    reducedRateInPercentage = reducedRateInPercentage
)

fun ShopOrder.enrich() = EnrichedOrder(
    id = id,
    version = version,
    items = items.map { it.enrich() },
    coupons = coupons.map { it.enrich() },
    orderTimeStamp = LocalDateTime.of(
        LocalDate.of(2024, 7, 19),
        LocalTime.of(11, 45)
    ),
    deliveryDate = deliveryDate,
    shippingCost = listOf(
        EnrichedPrice(
            value = 500,
            monetaryUnit = "cent",
            currency = "EUR"
        )
    ),
    customer = customer.enrich(),
    shippingAddress = shippingAddress.enrich(latitude = "50.96490882194811", longitude = "7.014472855463499"),
    billingAddress = billingAddress.enrich(latitude = "50.94603935915518", longitude = "6.959302840118697"),
)