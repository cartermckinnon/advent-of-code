package year2022.day3;

import static java.lang.Character.isUpperCase;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author carter
 */
public class Extension {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("usage: Extension DATA_FILE");
      System.exit(1);
    }
    int total = 0;
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      while (scanner.hasNextLine()) {
        Set<Character> intersection = new HashSet<>();
        for (int elf = 0; elf < 3; elf++) {
          Set<Character> rucksack = new HashSet<>();
          char[] items = scanner.nextLine().toCharArray();
          for (char item : items) {
            rucksack.add(item);
          }
          if (elf == 0) {
            intersection.addAll(rucksack);
          } else {
            intersection.retainAll(rucksack);
          }
        }
        char badge = intersection.iterator().next();
        if (isUpperCase(badge)) {
          total += 27 + (badge - 'A');
        } else {
          total += 1 + (badge - 'a');
        }
      }
    }
    System.out.println(total);
  }
}
