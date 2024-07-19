package io.github.mkutz.qac.approvaltesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.github.mkutz.qac.approvaltesting.AddressBuilder.anAddress;
import static io.github.mkutz.qac.approvaltesting.CouponBuilder.aCoupon;
import static io.github.mkutz.qac.approvaltesting.CustomerBuilder.aCustomer;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.callRestEndpoint;
import static io.github.mkutz.qac.approvaltesting.ItemBuilder.anItem;
import static io.github.mkutz.qac.approvaltesting.OrderBuilder.anOrder;
import static io.github.mkutz.qac.approvaltesting.PriceBuilder.aPrice;
import static org.assertj.core.api.Assertions.assertThat;

class JManyAssertionsTest {

    @Test
    void assertionTest() throws JsonProcessingException {
        String orderId = "someOrderId";
        Order order = anOrder()
                .id(orderId)
                .version(1)
                .items(List.of(anItem()
                                .id("someItemId")
                                .name("ATD 3 Conf. Days")
                                .amount(2)
                                .price(
                                        aPrice()
                                                .value(225000)
                                                .monetaryUnit("cent")
                                                .currency("EUR")
                                                .build()
                                )
                                .build()
                        )
                )
                .coupons(List.of(
                        aCoupon()
                                .id("someCouponId")
                                .description("Speaker Coupon")
                                .reducedRateInPercentage(100)
                                .build()
                ))
                .deliveryDate(LocalDate.of(2024, 11, 22))
                .customer(aCustomer()
                        .id("someCustomerId")
                        .firstName("REWE")
                        .lastName("Digital")
                        .build()
                )
                .shippingAddress(anAddress()
                        .id("someShippingAddressId")
                        .firstName("Janina")
                        .lastName("Nemec")
                        .streetName("Schanzenstr.")
                        .houseNumber("6-20")
                        .postalCode("51063")
                        .city("Köln")
                        .country("Deutschland")
                        .phone("0221 9758420")
                        .latitude("50.96490882194811")
                        .longitude("7.014472855463499")
                        .email("kontakt@rewe-digital.com")
                        .build()
                )
                .billingAddress(anAddress()
                        .id("someBillingAddressId")
                        .firstName("Micha")
                        .lastName("Kutz")
                        .streetName("Domstr.")
                        .houseNumber("20")
                        .postalCode("50668")
                        .city("Köln")
                        .country("Deutschland")
                        .phone("+49 221 1490")
                        .latitude("50.94603935915518")
                        .longitude("6.959302840118697")
                        .email("info@rewe-group.com")
                        .build()
                )
                .build();

        anOrderWasProcessed(order);

        JsonNode result = TestUtils.jsonMapper.readTree(callRestEndpoint(orderId));

        assertThat(result.get("id").asText()).isEqualTo("someOrderId");
        assertThat(result.get("version").asText()).isEqualTo("1");

        assertThat(result.get("items").get(0).get("id").asText()).isEqualTo("someItemId");
        assertThat(result.get("items").get(0).get("name").asText()).isEqualTo("ATD 3 Conf. Days");
        assertThat(result.get("items").get(0).get("amount").asText()).isEqualTo("2");
        assertThat(result.get("items").get(0).get("price").get("value").asText()).isEqualTo("225000");
        assertThat(result.get("items").get(0).get("price").get("monetaryUnit").asText()).isEqualTo("cent");
        assertThat(result.get("items").get(0).get("price").get("currency").asText()).isEqualTo("EUR");

        assertThat(result.get("coupons").get(0).get("id").asText()).isEqualTo("someCouponId");
        assertThat(result.get("coupons").get(0).get("description").asText()).isEqualTo("Speaker Coupon");
        assertThat(result.get("coupons").get(0).get("reducedRateInPercentage").asText()).isEqualTo("100");

        assertThat(result.get("orderTimeStamp").get(0).asInt()).isEqualTo(2024);
        assertThat(result.get("orderTimeStamp").get(1).asInt()).isEqualTo(7);
        assertThat(result.get("orderTimeStamp").get(2).asInt()).isEqualTo(19);
        assertThat(result.get("orderTimeStamp").get(3).asInt()).isEqualTo(11);
        assertThat(result.get("orderTimeStamp").get(4).asInt()).isEqualTo(45);

        assertThat(result.get("deliveryDate").get(0).asInt()).isEqualTo(2024);
        assertThat(result.get("deliveryDate").get(1).asInt()).isEqualTo(11);
        assertThat(result.get("deliveryDate").get(2).asInt()).isEqualTo(22);

        assertThat(result.get("shippingCost").get(0).get("value").asText()).isEqualTo("500");
        assertThat(result.get("shippingCost").get(0).get("monetaryUnit").asText()).isEqualTo("cent");
        assertThat(result.get("shippingCost").get(0).get("currency").asText()).isEqualTo("EUR");

        assertThat(result.get("customer").get("id").asText()).isEqualTo("someCustomerId");
        assertThat(result.get("customer").get("firstName").asText()).isEqualTo("REWE");
        assertThat(result.get("customer").get("lastName").asText()).isEqualTo("Digital");

        assertThat(result.get("shippingAddress").get("id").asText()).isEqualTo("someShippingAddressId");
        assertThat(result.get("shippingAddress").get("firstName").asText()).isEqualTo("Janina");
        assertThat(result.get("shippingAddress").get("lastName").asText()).isEqualTo("Nemec");
        assertThat(result.get("shippingAddress").get("streetName").asText()).isEqualTo("Schanzenstr.");
        assertThat(result.get("shippingAddress").get("houseNumber").asText()).isEqualTo("6-20");
        assertThat(result.get("shippingAddress").get("postalCode").asText()).isEqualTo("51063");
        assertThat(result.get("shippingAddress").get("city").asText()).isEqualTo("Köln");
        assertThat(result.get("shippingAddress").get("country").asText()).isEqualTo("Deutschland");
        assertThat(result.get("shippingAddress").get("phone").asText()).isEqualTo("0221 9758420");
        assertThat(result.get("shippingAddress").get("latitude").asText()).isEqualTo("50.96490882194811");
        assertThat(result.get("shippingAddress").get("longitude").asText()).isEqualTo("7.014472855463499");
        assertThat(result.get("shippingAddress").get("email").asText()).isEqualTo("kontakt@rewe-digital.com");

        assertThat(result.get("billingAddress").get("id").asText()).isEqualTo("someBillingAddressId");
        assertThat(result.get("billingAddress").get("firstName").asText()).isEqualTo("Micha");
        assertThat(result.get("billingAddress").get("lastName").asText()).isEqualTo("Kutz");
        assertThat(result.get("billingAddress").get("streetName").asText()).isEqualTo("Domstr.");
        assertThat(result.get("billingAddress").get("houseNumber").asText()).isEqualTo("20");
        assertThat(result.get("billingAddress").get("postalCode").asText()).isEqualTo("50668");
        assertThat(result.get("billingAddress").get("city").asText()).isEqualTo("Köln");
        assertThat(result.get("billingAddress").get("country").asText()).isEqualTo("Deutschland");
        assertThat(result.get("billingAddress").get("phone").asText()).isEqualTo("+49 221 1490");
        assertThat(result.get("billingAddress").get("latitude").asText()).isEqualTo("50.94603935915518");
        assertThat(result.get("billingAddress").get("longitude").asText()).isEqualTo("6.959302840118697");
        assertThat(result.get("billingAddress").get("email").asText()).isEqualTo("info@rewe-group.com");
    }
}
