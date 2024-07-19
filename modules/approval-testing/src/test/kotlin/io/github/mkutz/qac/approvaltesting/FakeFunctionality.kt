package io.github.mkutz.qac.approvaltesting

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule

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
    return jsonMapper.writeValueAsString(savedOrders.remove(orderId))
}
