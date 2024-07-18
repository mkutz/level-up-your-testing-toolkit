package io.github.mkutz.qac.mutationtesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FizzBuzzTest {

  FizzBuzz fizzBuzz = new FizzBuzz();

  @Test
  void fizzbuzz_1() {
    assertThat(fizzBuzz.fizzbuzz(1)).isEqualTo("1");
  }

  @Test
  void fizzbuzz_3() {
    assertThat(fizzBuzz.fizzbuzz(3)).isEqualTo("Fizz");
  }

  @Test
  void fizzbuzz_5() {
    assertThat(fizzBuzz.fizzbuzz(5)).isEqualTo("Buzz");
  }
}
