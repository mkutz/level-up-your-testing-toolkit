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

fun postRestEndpoint(shopOrderString: String) {
    val shopOrder = jsonMapper.readValue<ShopOrder>(shopOrderString, ShopOrder::class.java)
    anOrderWasProcessed(shopOrder)
}

fun callRestEndpoint(orderId: String): String? {
    return jsonMapper.writeValueAsString(savedOrders.get(orderId)!!)
}

fun callRestEndpointForBillingAddress(orderId: String): String? {
    return jsonMapper.writeValueAsString(savedOrders.get(orderId)!!.billingAddress)
}

