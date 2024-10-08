package io.github.mkutz.qac.approvaltesting;

import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.mkutz.qac.approvaltesting.FakeFunctionalityKt.callRestEndpoint;
import static io.github.mkutz.qac.approvaltesting.TestOrderBuilderKt.aDefaultOrder;

import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;

public class JApprovalTest {

  @Test
  void approvalTest() {
    String orderId = "someOrderId";
    ShopOrder order = aDefaultOrder(orderId);

    anOrderWasProcessed(order);

    JsonApprovals.verifyJson(callRestEndpoint(orderId));
  }
}
