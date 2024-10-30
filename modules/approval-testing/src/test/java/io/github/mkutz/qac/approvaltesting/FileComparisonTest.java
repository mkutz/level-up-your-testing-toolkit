package io.github.mkutz.qac.approvaltesting;

import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.callRestEndpoint;
import static io.github.mkutz.qac.approvaltesting.TestOrderBuilderKt.aDefaultOrder;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class FileComparisonTest {

  private String TEST_DIR = "src/test/resources/json/FileComparisonTest/";

  @Test
  void assertionTest() throws IOException {
    String orderId = "someOrderId";
    ShopOrder order = aDefaultOrder(orderId);

    anOrderWasProcessed(order);

    String result = callRestEndpoint(orderId);

    String expected = new String(Files.readAllBytes(Paths.get(TEST_DIR + "expectedOrder.json")));
    assertThat(result).isEqualToIgnoringWhitespace(expected);
  }
}
