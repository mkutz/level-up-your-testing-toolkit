package io.github.mkutz.qac.mutationtesting;

import static io.github.mkutz.qac.mutationtesting.FizzBuzz.fizzbuzz;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class FizzBuzzTest {

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
}
