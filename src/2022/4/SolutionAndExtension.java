package mck.adventofcode.year2022;

import java.io.File;
import java.util.Scanner;

/** @author carter */
public class SolutionWithExtension {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("usage: SolutionWithExtension DATA_FILE");
      System.exit(1);
    }
    int contains = 0;
    int overlaps = 0;
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      while (scanner.hasNextLine()) {
        String[] ranges = scanner.nextLine().split(",");
        Range left = Range.parse(ranges[0]);
        Range right = Range.parse(ranges[1]);
        if (left.contains(right) || right.contains(left)) {
          contains++;
        }
        if (left.overlaps(right) || right.overlaps(left)) {
          overlaps++;
        }
      }
    }
    System.out.println(contains);
    System.out.println(overlaps); // extension
  }

  public static class Range {
    final int start;
    final int end;

    public static Range parse(String s) {
      String[] ints = s.split("-");
      return new Range(Integer.parseInt(ints[0]), Integer.parseInt(ints[1]));
    }

    public Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public boolean contains(Range other) {
      return this.start <= other.start && other.end <= this.end;
    }

    public boolean contains(int n) {
      return this.start <= n && n <= this.end;
    }

    public boolean overlaps(Range other) {
      return this.contains(other.start) || this.contains(other.end);
    }
  }
}
