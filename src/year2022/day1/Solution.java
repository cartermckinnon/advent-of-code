package year2022.day1;

import java.io.File;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) throws Exception {
    int max = 0;
    int elf = 0;
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.isBlank()) {
          max = Math.max(max, elf);
          elf = 0;
        } else {
          elf += Integer.parseInt(line);
        }
      }
    }
    System.out.println(max);
  }
}
