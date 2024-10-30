package io.github.mkutz.qac.approvaltesting

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.readValue
import java.time.LocalDate
import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManyObjectAssertionsTest {

    @Test
    @Throws(JsonProcessingException::class)
    fun assertionTest() {
        val orderId = "someOrderId"
        val order = aDefaultOrder(orderId)

        anOrderWasProcessed(order)

        val orderResult = jsonMapper.readValue<OrderResult>(callRestEndpoint(orderId)!!)

        assertThat(orderResult.id).isEqualTo("someOrderId")
        assertThat(orderResult.version).isEqualTo(1)

        val item: ItemResult = orderResult.items.first()
        assertThat(item.id).isEqualTo("someItemId")
        assertThat(item.name).isEqualTo("ATD 3 Conf. Days")
        assertThat(item.amount).isEqualTo(2)

        val itemPrice = item.price
        assertThat(itemPrice.value).isEqualTo(225000)
        assertThat(itemPrice.monetaryUnit).isEqualTo("cent")
        assertThat(itemPrice.currency).isEqualTo("EUR")

        val coupon: CouponResult = orderResult.coupons.first()
        assertThat(coupon.id).isEqualTo("someCouponId")
        assertThat(coupon.description).isEqualTo("Speaker Coupon")
        assertThat(coupon.reducedRateInPercentage).isEqualTo(100)

        assertThat(orderResult.orderTimeStamp).isEqualTo(LocalDateTime.of(2024, 7, 19, 11, 45))
        assertThat(orderResult.deliveryDate).isEqualTo(LocalDate.of(2024, 11, 22))

        val shippingCost: PriceResult = orderResult.shippingCost.first()
        assertThat(shippingCost.value).isEqualTo(500)
        assertThat(shippingCost.monetaryUnit).isEqualTo("cent")
        assertThat(shippingCost.currency).isEqualTo("EUR")

        val customer = orderResult.customer
        assertThat(customer.id).isEqualTo("someCustomerId")
        assertThat(customer.firstName).isEqualTo("REWE")
        assertThat(customer.lastName).isEqualTo("Digital")

        val shippingAddress = orderResult.shippingAddress
        assertThat(shippingAddress.id).isEqualTo("someShippingAddressId")
        assertThat(shippingAddress.firstName).isEqualTo("Janina")
        assertThat(shippingAddress.lastName).isEqualTo("Nemec")
        assertThat(shippingAddress.streetName).isEqualTo("Schanzenstr.")
        assertThat(shippingAddress.houseNumber).isEqualTo("6-20")
        assertThat(shippingAddress.postalCode).isEqualTo("51063")
        assertThat(shippingAddress.city).isEqualTo("Köln")
        assertThat(shippingAddress.country).isEqualTo("Deutschland")
        assertThat(shippingAddress.phone).isEqualTo("0221 9758420")
        assertThat(shippingAddress.latitude).isEqualTo("50.96490882194811")
        assertThat(shippingAddress.longitude).isEqualTo("7.014472855463499")
        assertThat(shippingAddress.email).isEqualTo("kontakt@rewe-digital.com")

        val billingAddress = orderResult.billingAddress
        assertThat(billingAddress.id).isEqualTo("someBillingAddressId")
        assertThat(billingAddress.firstName).isEqualTo("Micha")
        assertThat(billingAddress.lastName).isEqualTo("Kutz")
        assertThat(billingAddress.streetName).isEqualTo("Domstr.")
        assertThat(billingAddress.houseNumber).isEqualTo("20")
        assertThat(billingAddress.postalCode).isEqualTo("50668")
        assertThat(billingAddress.city).isEqualTo("Köln")
        assertThat(billingAddress.country).isEqualTo("Deutschland")
        assertThat(billingAddress.phone).isEqualTo("+49 221 1490")
        assertThat(billingAddress.latitude).isEqualTo("50.94603935915518")
        assertThat(billingAddress.longitude).isEqualTo("6.959302840118697")
        assertThat(billingAddress.email).isEqualTo("info@rewe-group.com")
    }
}