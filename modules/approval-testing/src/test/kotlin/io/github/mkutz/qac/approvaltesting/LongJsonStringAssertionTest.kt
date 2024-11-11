package io.github.mkutz.qac.approvaltesting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class LongJsonStringAssertionTest {

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

        val result = callRestEndpoint(orderId)

        assertThat(result).isEqualToIgnoringWhitespace(
            """
{
  "id": "someOrderId",
  "version": 1,
  "items": [
    {
      "id": "someItemId",
      "name": "ATD 3 Conf. Days",
      "amount": 2,
      "price": {
        "value": 225000,
        "monetaryUnit": "cent",
        "currency": "EUR"
      }
    }
  ],
  "coupons": [
    {
      "id": "someCouponId",
      "description": "Speaker Coupon",
      "reducedRateInPercentage": 100
    }
  ],
  "orderTimeStamp": [
    2024,
    7,
    19,
    11,
    45
  ],
  "deliveryDate": [
    2024,
    11,
    22
  ],
  "shippingCost": [
    {
      "value": 500,
      "monetaryUnit": "cent",
      "currency": "EUR"
    }
  ],
  "customer": {
    "id": "someCustomerId",
    "firstName": "REWE",
    "lastName": "Digital"
  },
  "shippingAddress": {
    "id": "someShippingAddressId",
    "firstName": "Janina",
    "lastName": "Nemec",
    "streetName": "Schanzenstr.",
    "houseNumber": "6-20",
    "city": "Köln",
    "country": "Deutschland",
    "phone": "0221 9758420",
    "latitude": "50.96490882194811",
    "longitude": "7.014472855463499",
    "email": "kontakt@rewe-digital.com",
    "postalCode": "51063",
    "status":"known_customer"
  },
  "billingAddress": {
    "id": "someBillingAddressId",
    "firstName": "Micha",
    "lastName": "Kutz",
    "streetName": "Domstr.",
    "houseNumber": "20",
    "city": "Köln",
    "country": "Deutschland",
    "phone": "+49 221 1490",
    "latitude": "50.94603935915518",
    "longitude": "6.959302840118697",
    "email": "info@rewe-group.com",
    "postalCode": "50668",
    "status":"new_customer"
  }
}            
        """
        )
    }

}
