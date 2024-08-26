package io.github.mkutz.qac.approvaltesting;

import static io.github.mkutz.qac.approvaltesting.AddressBuilder.anAddress;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.callRestEndpointForBillingAddress;
import static io.github.mkutz.qac.approvaltesting.TestOrderBuilderKt.anyOrder;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

class AddressAssertionTest {
  @Test
  void assertionTest() throws JsonProcessingException {
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

    JsonNode result = TestUtils.jsonMapper.readTree(callRestEndpointForBillingAddress(orderId));
    assertThat(result.get("id").asText()).isEqualTo("someBillingAddressId");
    assertThat(result.get("firstName").asText()).isEqualTo("Micha");
    assertThat(result.get("lastName").asText()).isEqualTo("Kutz");
    assertThat(result.get("streetName").asText()).isEqualTo("Domstr.");
    assertThat(result.get("houseNumber").asText()).isEqualTo("20");
    assertThat(result.get("postalCode").asText()).isEqualTo("50668");
    assertThat(result.get("city").asText()).isEqualTo("Köln");
    assertThat(result.get("country").asText()).isEqualTo("Deutschland");
    assertThat(result.get("phone").asText()).isEqualTo("+49 221 1490");
    assertThat(result.get("latitude").asText()).isEqualTo("50.94603935915518");
    assertThat(result.get("longitude").asText()).isEqualTo("6.959302840118697");
    assertThat(result.get("email").asText()).isEqualTo("info@rewe-group.com");
  }
}
