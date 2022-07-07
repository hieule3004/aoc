package aoc;

import aoc.utils.DailyChallenge;
import aoc.y2021.D07TheTreacheryOfWhales;

import java.util.Scanner;

public class Main {


  private static void mainInternal() {
    DailyChallenge challenge = new D07TheTreacheryOfWhales();
    long start = System.currentTimeMillis();
    System.out.println(challenge.q1());
    long mid = System.currentTimeMillis();
    System.out.printf("Q1 = %d ms%n", mid - start);
    System.out.println(challenge.q2());
    System.out.printf("Q2 = %d ms%n", System.currentTimeMillis() - mid);
  }

  public static void main(String[] args) {
    mainInternal();

    Scanner scanner = new Scanner(System.in);
    while (true) {
      try {
        System.out.println("Again (y/n): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty() || Character.toLowerCase(input.charAt(0)) != 'y') break;
        mainInternal();
      } catch (Exception e) {
        e.printStackTrace();
        break;
      }
    }
  }
}
