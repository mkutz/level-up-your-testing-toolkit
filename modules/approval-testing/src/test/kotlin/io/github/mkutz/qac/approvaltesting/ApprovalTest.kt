package io.github.mkutz.qac.approvaltesting

import org.approvaltests.JsonApprovals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ApprovalTest {

    @Test
    fun approvalTest() {
        val orderId = "someOrderId"
        val shopOrder = ShopOrder(
            id = orderId,
            version = 1,
            items = listOf(
                ShopItem(
                    id = "someItemId",
                    name = "ATD 3 Conf. Days",
                    amount = 2,
                    price = ShopPrice(
                        value = 225000,
                        monetaryUnit = "cent",
                        currency = "EUR"
                    )
                )
            ),
            coupons = listOf(
                ShopCoupon(
                    id = "someCouponId",
                    description = "Speaker Coupon",
                    reducedRateInPercentage = 100
                )
            ),
            deliveryDate = LocalDate.of(2024, 11, 22),
            customer = ShopCustomer(
                id = "someCustomerId",
                firstName = "REWE",
                lastName = "Digital"
            ),
            shippingAddress = ShopAddress(
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
                email = "kontakt@rewe-digital.com"
            ),
            billingAddress = ShopAddress(
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
                email = "info@rewe-group.com",
            )
        )
        anOrderWasProcessed(shopOrder)

        val result = callRestEndpoint(orderId)

        JsonApprovals.verifyJson(result)
    }

}
