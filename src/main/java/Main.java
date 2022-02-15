import utils.LinkUtils;
import y2021.D03BinaryDiagnostic;
import y2021.D04GiantSquid;
import y2021.D05HydrothermalVenture;

import java.util.Scanner;

public class Main {


  private static void mainInternal() {
    System.out.println(D05HydrothermalVenture.q1());
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
