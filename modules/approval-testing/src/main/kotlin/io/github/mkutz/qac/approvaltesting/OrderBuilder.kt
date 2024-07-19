package io.github.mkutz.qac.approvaltesting

import java.time.LocalDate
import java.time.LocalDateTime

class OrderBuilder {
    private var id: String? = null
    private var version: Int? = null
    private var items: List<Item>? = null
    private var coupons: List<Coupon>? = null
    private var orderTimeStamp: LocalDateTime? = null
    private var deliveryDate: LocalDate? = null
    private var shippingCost: List<Price>? = null
    private var customer: Customer? = null
    private var shippingAddress: Address? = null
    private var billingAddress: Address? = null

    fun id(id: String?): OrderBuilder {
        this.id = id
        return this
    }

    fun version(version: Int): OrderBuilder {
        this.version = version
        return this
    }

    fun items(items: List<Item>?): OrderBuilder {
        this.items = items
        return this
    }

    fun coupons(coupons: List<Coupon>?): OrderBuilder {
        this.coupons = coupons
        return this
    }

    fun orderTimeStamp(orderTimeStamp: LocalDateTime?): OrderBuilder {
        this.orderTimeStamp = orderTimeStamp
        return this
    }

    fun deliveryDate(deliveryDate: LocalDate?): OrderBuilder {
        this.deliveryDate = deliveryDate
        return this
    }

    fun shippingCost(shippingCost: List<Price>?): OrderBuilder {
        this.shippingCost = shippingCost
        return this
    }

    fun customer(customer: Customer?): OrderBuilder {
        this.customer = customer
        return this
    }

    fun shippingAddress(shippingAddress: Address?): OrderBuilder {
        this.shippingAddress = shippingAddress
        return this
    }

    fun billingAddress(billingAddress: Address?): OrderBuilder {
        this.billingAddress = billingAddress
        return this
    }

    fun build(): Order {
        return Order(
            id!!,
            version!!,
            items!!,
            coupons!!,
            orderTimeStamp!!,
            deliveryDate!!,
            shippingCost!!,
            customer!!,
            shippingAddress!!,
            billingAddress!!
        )
    }

    companion object {
        @JvmStatic
        fun anOrder(): OrderBuilder {
            return OrderBuilder()
        }
    }
}
