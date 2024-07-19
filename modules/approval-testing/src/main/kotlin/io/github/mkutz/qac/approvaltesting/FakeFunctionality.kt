package io.github.mkutz.qac.approvaltesting

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val jsonMapper: JsonMapper = JsonMapper
    .builder()
    .addModule(JavaTimeModule())
    .addModule(kotlinModule())
    .build()

val savedOrders = mutableMapOf<String, Order>()

fun anOrderWasProcessed(order: Order) {
    savedOrders[order.id] = order
}

fun callRestEndpoint(orderId: String): String? {
    val order = savedOrders.remove(orderId)!!.copy(
        orderTimeStamp = LocalDateTime.of(
            LocalDate.of(2024, 7, 19),
            LocalTime.of(11, 45)
        ),
        shippingCost = listOf(
            Price(
                value = 500,
                monetaryUnit = "cent",
                currency = "EUR"
            )
        ),
    )

    return jsonMapper.writeValueAsString(order)
}
