package io.github.mkutz.qac.approvaltesting

import java.time.LocalDate
import java.time.LocalDateTime

class OrderBuilder {
    private var id: String? = null
    private var version: Int? = null
    private var shopItems: List<ShopItem>? = null
    private var shopCoupons: List<ShopCoupon> = emptyList()
    private var orderTimeStamp: LocalDateTime? = null
    private var deliveryDate: LocalDate? = null
    private var shippingCost: List<ShopPrice> = emptyList()
    private var shopCustomer: ShopCustomer? = null
    private var shippingShopAddress: ShopAddress? = null
    private var billingShopAddress: ShopAddress? = null

    fun id(id: String): OrderBuilder {
        this.id = id
        return this
    }

    fun version(version: Int): OrderBuilder {
        this.version = version
        return this
    }

    fun items(shopItems: List<ShopItem>): OrderBuilder {
        this.shopItems = shopItems
        return this
    }

    fun coupons(shopCoupons: List<ShopCoupon>): OrderBuilder {
        this.shopCoupons = shopCoupons
        return this
    }

    fun orderTimeStamp(orderTimeStamp: LocalDateTime): OrderBuilder {
        this.orderTimeStamp = orderTimeStamp
        return this
    }

    fun deliveryDate(deliveryDate: LocalDate): OrderBuilder {
        this.deliveryDate = deliveryDate
        return this
    }

    fun shippingCost(shippingCost: List<ShopPrice>): OrderBuilder {
        this.shippingCost = shippingCost
        return this
    }

    fun customer(shopCustomer: ShopCustomer): OrderBuilder {
        this.shopCustomer = shopCustomer
        return this
    }

    fun shippingAddress(shippingShopAddress: ShopAddress): OrderBuilder {
        this.shippingShopAddress = shippingShopAddress
        return this
    }

    fun billingAddress(billingShopAddress: ShopAddress): OrderBuilder {
        this.billingShopAddress = billingShopAddress
        return this
    }

    fun build(): ShopOrder {
        return ShopOrder(
            id = id!!,
            version = version!!,
            items = shopItems!!,
            coupons = shopCoupons,
            orderTimeStamp = orderTimeStamp,
            deliveryDate = deliveryDate!!,
            shippingCost = shippingCost,
            customer = shopCustomer!!,
            shippingAddress = shippingShopAddress!!,
            billingAddress = billingShopAddress!!
        )
    }

    companion object {
        @JvmStatic
        fun anOrder(): OrderBuilder {
            return OrderBuilder()
        }
    }
}
