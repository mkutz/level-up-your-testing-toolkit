package io.github.mkutz.qac.propertybasedtesting;

import static io.github.mkutz.qac.propertybasedtesting.FizzBuzz.fizzbuzz;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class SingleExamplesTest {

  @Test
  void fizzbuzz_1() {
    assertThat(fizzbuzz(1)).isEqualTo("1");
  }

  @Test
  void fizzbuzz_3() {
    assertThat(fizzbuzz(3)).isEqualTo("Fizz");
  }

  @Test
  void fizzbuzz_5() {
    assertThat(fizzbuzz(5)).isEqualTo("Buzz");
  }

  @Test
  void fizzbuzz_15() {
    assertThat(fizzbuzz(15)).isEqualTo("FizzBuzz");
  }
}
