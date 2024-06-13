package io.github.mkutz.qac.propertybasedtesting;

import static io.github.mkutz.qac.propertybasedtesting.FizzBuzz.fizzbuzz;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParametrizedTest {

  @ParameterizedTest(name = "fizzbuzz({0})")
  @ValueSource(ints = {1, 2, 4, 7, 8, 11, 13, 14})
  void fizzbuzz_not_divisible_by_3_or_5(int notDivisibleBy3Or5) {
    assertThat(fizzbuzz(notDivisibleBy3Or5)).isEqualTo(String.valueOf(notDivisibleBy3Or5));
  }

  @ParameterizedTest(name = "fizzbuzz({0})")
  @ValueSource(ints = {3, 6, 9, 12, 18, 21, 24, 27})
  void fizzbuzz_divisible_by_3(int divisibleBy3) {
    assertThat(fizzbuzz(divisibleBy3)).isEqualTo("Fizz");
  }

  @ParameterizedTest(name = "fizzbuzz({0})")
  @ValueSource(ints = {5, 10, 20, 25, 35, 40, 50, 55})
  void fizzbuzz_divisible_by_5(int divisibleBy5) {
    assertThat(fizzbuzz(divisibleBy5)).isEqualTo("Buzz");
  }

  @ParameterizedTest(name = "fizzbuzz({0})")
  @ValueSource(ints = {15, 30, 45, 60, 75, 90, 105, 120})
  void fizzbuzz_divisible_by_3_and_5(int divisibleBy3And5) {
    assertThat(fizzbuzz(divisibleBy3And5)).isEqualTo("FizzBuzz");
  }
}
