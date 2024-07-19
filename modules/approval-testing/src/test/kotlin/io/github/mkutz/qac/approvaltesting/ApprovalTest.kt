package io.github.mkutz.qac.approvaltesting

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.approvaltests.JsonApprovals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ApprovalTest {

    private val jsonMapper = JsonMapper
        .builder()
        .addModule(JavaTimeModule())
        .addModule(kotlinModule())
        .build()

    private var savedOrder: Order? = null

    @Test
    fun approvalTest() {
        val orderId = "someOrderId"
        val order = Order(
            id = orderId,
            version = 1,
            items = listOf(
                Item(
                    id = "someItemId",
                    name = "ATD 3 Conf. Days",
                    amount = 2,
                    price = Price(
                        value = 225000,
                        monetaryUnit = "cent",
                        currency = "EUR"
                    )
                )
            ),
            coupons = listOf(
                Coupon(
                    id = "someCouponId",
                    description = "Speaker Coupon",
                    reducedRateInPercentage = 100
                )
            ),
            orderTimeStamp = LocalDateTime.of(LocalDate.of(2024, 7, 19), LocalTime.of(11, 45) ),
            deliveryDate = LocalDate.of(2024, 11, 22),
            shippingCost = listOf(
                Price(
                    value = 500,
                    monetaryUnit = "cent",
                    currency = "EUR"
                )),
            customer = Customer(
                id = "someCustomerId",
                firstName = "someFirstName",
                lastName = "someLastName"
            ),
            shippingAddress = Address(
                id = "someShippingAddressId",
                firstName = "Janina",
                lastName = "Nemec",
                streetName = "Schanzenstr.",
                houseNumber = "6-20",
                postalCode = "51063",
                city = "Köln",
                country = "Deutschland",
                phone = "0221 9758420",
                latitude = "50.96490882194811",
                longitude = "7.014472855463499",
                eMail = "kontakt@rewe-digital.com"
            ),
            billingAddress = Address(
                id = "someBillingAddressId",
                firstName = "Micha",
                lastName = "Kutz",
                streetName = "Domstr.",
                houseNumber = "20",
                postalCode = "50668",
                city = "Köln",
                country = "Deutschland",
                phone = "+49 221 1490",
                latitude = "50.94603935915518",
                longitude = "6.959302840118697",
                eMail = "info@rewe-group.com",
            )
        )
        anOrderWasProcessed(order)

        val result = callRestEndpoint(orderId)

        JsonApprovals.verifyJson(result)
    }

    private fun anOrderWasProcessed(order: Order) {
        savedOrder = order
    }

    private fun callRestEndpoint(orderId: String): String? = jsonMapper.writeValueAsString(savedOrder)
}
