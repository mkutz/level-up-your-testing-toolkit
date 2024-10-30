package io.github.mkutz.qac.approvaltesting

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule

val jsonMapper: JsonMapper = JsonMapper
    .builder()
    .addModule(JavaTimeModule())
    .addModule(Jdk8Module())
    .addModule(kotlinModule())
    .build()

val savedOrders = mutableMapOf<String, OrderResult>()

fun anOrderWasProcessed(shopOrder: ShopOrder) {
    savedOrders[shopOrder.id] = shopOrder.enrich()
}

fun callRestEndpoint(orderId: String): String? {
    return jsonMapper.writeValueAsString(savedOrders.remove(orderId)!!)
}

fun callRestEndpointForBillingAddress(orderId: String): String? {
    return jsonMapper.writeValueAsString(savedOrders.remove(orderId)!!.billingAddress)
}

