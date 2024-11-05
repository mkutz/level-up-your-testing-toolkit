package io.github.mkutz.qac.approvaltesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.mkutz.qac.approvaltesting.AddressBuilder.anAddress;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.callRestEndpointForBillingAddress;
import static io.github.mkutz.qac.approvaltesting.TestOrderBuilderKt.anyOrder;
import static io.github.mkutz.qac.approvaltesting.TestUtils.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

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

    JsonNode result = jsonMapper.readTree(callRestEndpointForBillingAddress(orderId));
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

  @Test
  void assertionTest2() throws JsonProcessingException {
    String orderId = "someOrderId";
    ShopOrder shopOrder = anyOrder(orderId)
        .billingAddress(anAddress().id("someBillingAddressId")
            .firstName("Janina").lastName("Nemec")
            .streetName("Domstr.").houseNumber("20")
            .postalCode("50668").city("Köln").country("Deutschland")
            .phone("+49 221 1490").email("info@rewe-group.com").build()
        ).build();

    anOrderWasProcessed(shopOrder);

    String content = callRestEndpointForBillingAddress(orderId);
    AddressResult billingAddress = jsonMapper.readValue(content, AddressResult.class);
    assertThat(billingAddress.getId()).isEqualTo("someBillingAddressId");
    assertThat(billingAddress.getFirstName()).isEqualTo("Janina");
    assertThat(billingAddress.getLastName()).isEqualTo("Nemec");
    assertThat(billingAddress.getStreetName()).isEqualTo("Domstr.");
    assertThat(billingAddress.getHouseNumber()).isEqualTo("20");
    assertThat(billingAddress.getPostalCode()).isEqualTo("50668");
    assertThat(billingAddress.getCity()).isEqualTo("Köln");
    assertThat(billingAddress.getCountry()).isEqualTo("Deutschland");
    assertThat(billingAddress.getPhone()).isEqualTo("+49 221 1490");
    assertThat(billingAddress.getLatitude()).isEqualTo("50.94603935915518");
    assertThat(billingAddress.getLongitude()).isEqualTo("6.959302840118697");
    assertThat(billingAddress.getStatus()).isEqualTo(CustomerStatus.NEW_CUSTOMER);
    assertThat(billingAddress.getEmail()).isEqualTo("info@rewe-group.com");
  }

  @Test
  void assertionTest3() {
    String orderId = "someOrderId";
    ShopOrder shopOrder = anyOrder(orderId)
        .billingAddress(anAddress().id("someBillingAddressId")
            .firstName("Janina").lastName("Nemec")
            .streetName("Domstr.").houseNumber("20")
            .postalCode("50668").city("Köln").country("Deutschland")
            .phone("+49 221 1490").email("info@rewe-group.com").build()
        ).build();

    anOrderWasProcessed(shopOrder);

    assertThat(callRestEndpointForBillingAddress(orderId)).isEqualToIgnoringWhitespace("""
        {
          "id": "someBillingAddressId",
          "firstName": "Janina",
          "lastName": "Nemec",
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
        }""");
  }

  private final String TEST_DIR = "src/test/resources/json/FileComparisonTest/";

  @Test
  void assertionTest4() throws IOException {
    String orderId = "someOrderId";
    ShopOrder shopOrder = anyOrder(orderId)
        .billingAddress(anAddress().id("someBillingAddressId")
            .firstName("Janina").lastName("Nemec")
            .streetName("Domstr.").houseNumber("20")
            .postalCode("50668").city("Köln").country("Deutschland")
            .phone("+49 221 1490").email("info@rewe-group.com").build()
        ).build();

    anOrderWasProcessed(shopOrder);

    String result = callRestEndpointForBillingAddress(orderId);
    String expected = new String(Files.readAllBytes(Paths.get(TEST_DIR + "expectedBillingAddress.json")));
    assertThat(result).isEqualToIgnoringWhitespace(expected);
  }
}
