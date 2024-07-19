package io.github.mkutz.qac.approvaltesting;

import org.approvaltests.JsonApprovals;
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

public class JApprovalTest {

    @Test
    void approvalTest() {
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

        String result = callRestEndpoint(orderId);

        JsonApprovals.verifyJson(result);
    }
}
