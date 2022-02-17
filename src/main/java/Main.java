import utils.DailyChallenge;
import y2021.D05HydrothermalVenture;
import y2021.D06Lanternfish;

import java.util.Scanner;

public class Main {


  private static void mainInternal() {
    DailyChallenge challenge = new D06Lanternfish();
    System.out.println(challenge.q1());
    System.out.println(challenge.q2());
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
