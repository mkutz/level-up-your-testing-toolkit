package io.github.mkutz.qac.approvaltesting;

import static io.github.mkutz.qac.approvaltesting.AddressBuilder.anAddress;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.callRestEndpointForBillingAddress;
import static io.github.mkutz.qac.approvaltesting.TestOrderBuilderKt.anyOrder;

import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;

class AddressApprovalTest {
  @Test
  void assertionTest() {
    String orderId = "someOrderId";
    ShopOrder shopOrder =
        anyOrder(orderId)
            .billingAddress(
                anAddress()
                    .id("someBillingAddressId")
                    .firstName("Micha")
                    .lastName("Kutz")
                    .streetName("Domstr.")
                    .houseNumber("20")
                    .postalCode("50668")
                    .city("Köln")
                    .country("Deutschland")
                    .phone("+49 221 1490")
                    .email("info@rewe-group.com")
                    .build())
            .build();

    anOrderWasProcessed(shopOrder);

    JsonApprovals.verifyJson(callRestEndpointForBillingAddress(orderId));
  }
}
