package io.github.mkutz.qac.approvaltesting;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
  NEW_CUSTOMER,
  KNOWN_CUSTOMER;

  @JsonValue
  String value() {
    return this.name().toLowerCase();
  }
}
