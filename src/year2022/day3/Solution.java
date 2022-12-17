package year2022.day3;

import static java.lang.Character.isUpperCase;

import java.io.File;
import java.util.Scanner;

/**
 * @author carter
 */
public class Solution {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("usage: Solution DATA_FILE");
      System.exit(1);
    }
    int total = 0;
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      while (scanner.hasNextLine()) {
        char[] items = scanner.nextLine().toCharArray();
        int[] itemCounts = new int[128];
        for (int i = 0; i < items.length; i++) {
          char item = items[i];
          if (i < items.length / 2) {
            itemCounts[item] += 1;
          } else if (itemCounts[item] > 0) {
            if (isUpperCase(item)) {
              total += 27 + (item - 'A');
            } else {
              total += 1 + (item - 'a');
            }
            break;
          }
        }
      }
    }
    System.out.println(total);
  }
}
