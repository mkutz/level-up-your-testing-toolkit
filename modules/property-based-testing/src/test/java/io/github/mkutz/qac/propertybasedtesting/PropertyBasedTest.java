package io.github.mkutz.qac.propertybasedtesting;

import static io.github.mkutz.qac.propertybasedtesting.FizzBuzz.fizzbuzz;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

class PropertyBasedTest {

  @Property
  void fizzbuzz_not_divisible_by_3_or_5(@ForAll("notDivisibleBy3Or5") int notDivisibleBy3Or5) {
    assertThat(fizzbuzz(notDivisibleBy3Or5)).isEqualTo(String.valueOf(notDivisibleBy3Or5));
  }

  @Provide
  Arbitrary<Integer> notDivisibleBy3Or5() {
    return Arbitraries.integers().filter(i -> i % 3 != 0 && i % 5 != 0);
  }

  @Property
  void fizzbuzz_divisible_by_3(@ForAll("divisibleBy3") int divisibleBy3) {
    assertThat(fizzbuzz(divisibleBy3)).isEqualTo("Fizz");
  }

  @Provide
  Arbitrary<Integer> divisibleBy3() {
    return Arbitraries.integers().filter(i -> i % 3 == 0 && i % 5 != 0);
  }

  @Property
  void fizzbuzz_divisible_by_5(@ForAll("divisibleBy5") int divisibleBy5) {
    assertThat(fizzbuzz(divisibleBy5)).isEqualTo("Buzz");
  }

  @Provide
  Arbitrary<Integer> divisibleBy5() {
    return Arbitraries.integers().filter(i -> i % 3 != 0 && i % 5 == 0);
  }

  @Property
  void fizzbuzz_divisible_by_3_and_5(@ForAll("divisibleBy3And5") int divisibleBy3And5) {
    assertThat(fizzbuzz(divisibleBy3And5)).isEqualTo("FizzBuzz");
  }

  @Provide
  Arbitrary<Integer> divisibleBy3And5() {
    return Arbitraries.integers().filter(i -> i % 3 == 0 && i % 5 == 0);
  }
}
