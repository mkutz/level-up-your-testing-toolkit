package io.github.mkutz.qac.mutationtesting;

public class FizzBuzz {

  public static String fizzbuzz(int i) {
    if (i % 15 == 0) return "FizzBuzz";
    if (i % 3 == 0) return "Fizz";
    if (i % 5 == 0) return "Buzz";
    return String.valueOf(i);
  }
}
