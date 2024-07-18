package io.github.mkutz.qac.approvaltesting

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.approvaltests.JsonApprovals
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate

class ApprovalTest {

    private val jsonMapper = JsonMapper
        .builder()
//        .disable(WRITE_DATES_AS_TIMESTAMPS)
//        .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
//        .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
//        .enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
//        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
//        .addModule(JavaTimeModule())
//        .addModule(Jdk8Module())
        .addModule(kotlinModule())
        .build()

    @Test
    fun approvalTest() {
        val order = Order(
            id = "someOrderId",
            version = 1,
            items = emptyList(),
            coupons = emptyList(),
            orderTimeStamp = Instant.now(),
            deliveryDate = LocalDate.of(2024, 7, 18),
            shippingCost = listOf(Cost(5, "EUR")),
            customer = Customer(
                id = "someCustomerId",
                firstName = "someFirstName",
                lastName = "someLastName"
            ),
//            shippingAddress = Address(
//                id = "someShippingAddressId",
//                firstName = "Janina",
//                lastName = "Nemec",
//                streetName = "Schanzenstr.",
//                houseNumber = "6-20",
//                city = "Köln",
//            ),
//            billingAddress = Address(
//                id = "someBillingAddressId",
//            )
        )
        JsonApprovals.verifyJson(jsonMapper.writeValueAsString(order))
    }
}
