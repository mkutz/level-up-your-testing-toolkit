package io.github.mkutz.qac.approvaltesting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ManyJsonNodeAssertionsTest {

    @Test
    fun assertionTest() {
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
                city = "Köln",
                country = "Deutschland",
                phone = "0221 9758420",
                email = "kontakt@rewe-digital.com",
                postalCode = "51063"
            ),
            billingAddress = ShopAddress(
                id = "someBillingAddressId",
                firstName = "Micha",
                lastName = "Kutz",
                streetName = "Domstr.",
                houseNumber = "20",
                city = "Köln",
                country = "Deutschland",
                phone = "+49 221 1490",
                email = "info@rewe-group.com",
                postalCode = "50668",
            )
        )
        anOrderWasProcessed(shopOrder)

        val result = jsonMapper.readTree(callRestEndpoint(orderId))

        assertThat(result["id"].asText()).isEqualTo("someOrderId")
        assertThat(result["version"].asText()).isEqualTo("1")

        assertThat(result["items"][0]["id"].asText()).isEqualTo("someItemId")
        assertThat(result["items"][0]["name"].asText()).isEqualTo("ATD 3 Conf. Days")
        assertThat(result["items"][0]["amount"].asText()).isEqualTo("2")
        assertThat(result["items"][0]["price"]["value"].asText()).isEqualTo("225000")
        assertThat(result["items"][0]["price"]["monetaryUnit"].asText()).isEqualTo("cent")
        assertThat(result["items"][0]["price"]["currency"].asText()).isEqualTo("EUR")

        assertThat(result["coupons"][0]["id"].asText()).isEqualTo("someCouponId")
        assertThat(result["coupons"][0]["description"].asText()).isEqualTo("Speaker Coupon")
        assertThat(result["coupons"][0]["reducedRateInPercentage"].asText()).isEqualTo("100")

        assertThat(result["orderTimeStamp"].asText()).isEqualTo("2024-07-19T11:45:00")

        assertThat(result["deliveryDate"].asText()).isEqualTo("2024-11-22")

        assertThat(result["shippingCost"][0]["value"].asText()).isEqualTo("500")
        assertThat(result["shippingCost"][0]["monetaryUnit"].asText()).isEqualTo("cent")
        assertThat(result["shippingCost"][0]["currency"].asText()).isEqualTo("EUR")

        assertThat(result["customer"]["id"].asText()).isEqualTo("someCustomerId")
        assertThat(result["customer"]["firstName"].asText()).isEqualTo("REWE")
        assertThat(result["customer"]["lastName"].asText()).isEqualTo("Digital")

        assertThat(result["shippingAddress"]["id"].asText()).isEqualTo("someShippingAddressId")
        assertThat(result["shippingAddress"]["firstName"].asText()).isEqualTo("Janina")
        assertThat(result["shippingAddress"]["lastName"].asText()).isEqualTo("Nemec")
        assertThat(result["shippingAddress"]["streetName"].asText()).isEqualTo("Schanzenstr.")
        assertThat(result["shippingAddress"]["houseNumber"].asText()).isEqualTo("6-20")
        assertThat(result["shippingAddress"]["postalCode"].asText()).isEqualTo("51063")
        assertThat(result["shippingAddress"]["city"].asText()).isEqualTo("Köln")
        assertThat(result["shippingAddress"]["country"].asText()).isEqualTo("Deutschland")
        assertThat(result["shippingAddress"]["phone"].asText()).isEqualTo("0221 9758420")
        assertThat(result["shippingAddress"]["latitude"].asText()).isEqualTo("50.96490882194811")
        assertThat(result["shippingAddress"]["longitude"].asText()).isEqualTo("7.014472855463499")
        assertThat(result["shippingAddress"]["email"].asText()).isEqualTo("kontakt@rewe-digital.com")

        assertThat(result["billingAddress"]["id"].asText()).isEqualTo("someBillingAddressId")
        assertThat(result["billingAddress"]["firstName"].asText()).isEqualTo("Micha")
        assertThat(result["billingAddress"]["lastName"].asText()).isEqualTo("Kutz")
        assertThat(result["billingAddress"]["streetName"].asText()).isEqualTo("Domstr.")
        assertThat(result["billingAddress"]["houseNumber"].asText()).isEqualTo("20")
        assertThat(result["billingAddress"]["postalCode"].asText()).isEqualTo("50668")
        assertThat(result["billingAddress"]["city"].asText()).isEqualTo("Köln")
        assertThat(result["billingAddress"]["country"].asText()).isEqualTo("Deutschland")
        assertThat(result["billingAddress"]["phone"].asText()).isEqualTo("+49 221 1490")
        assertThat(result["billingAddress"]["latitude"].asText()).isEqualTo("50.94603935915518")
        assertThat(result["billingAddress"]["longitude"].asText()).isEqualTo("6.959302840118697")
        assertThat(result["billingAddress"]["email"].asText()).isEqualTo("info@rewe-group.com")
    }

}
