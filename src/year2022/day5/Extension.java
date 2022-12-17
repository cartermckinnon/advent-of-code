package year2022.day5;

import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Extension {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("usage: Extension INPUT_FILE");
      System.exit(1);
    }
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      List<String> stackInput = new ArrayList<>();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.isBlank()) {
          break;
        }
        stackInput.add(line);
      }
      Map<String, Stack> stacks = parseStacks(stackInput);
      while (scanner.hasNextLine()) {
        String[] parts = scanner.nextLine().split(" ");
        int n = Integer.parseInt(parts[1]);
        String sourceStack = parts[3];
        String destStack = parts[5];
        Deque<String> batch = new LinkedList<>();
        for (int i = 0; i < n; i++) {
          batch.push(stacks.get(sourceStack).pollTopCrate());
          ;
        }
        for (int i = 0; i < n; i++) {
          stacks.get(destStack).addTopCrate(batch.pop());
        }
      }
      StringBuilder result = new StringBuilder();
      for (Stack stack : stacks.values()) {
        result.append(stack.peekTopCrate());
      }
      System.out.println(result.toString());
    }
  }

  public static Map<String, Stack> parseStacks(List<String> lines) {
    Map<String, Stack> stacks =
        new TreeMap<>(); // TreeMap so the stacks can be iterated in order of their stackId
    String[] stackIds = lines.get(lines.size() - 1).trim().split(" +");
    for (int i = 0; i < stackIds.length; i++) {
      int startIndex = (i * 4) + 1;
      stacks.put(stackIds[i], new Stack(startIndex, startIndex + 1));
    }
    for (int i = 0; i < lines.size() - 1; i++) {
      for (Stack stack : stacks.values()) {
        stack.parseCrate(lines.get(i));
      }
    }
    return stacks;
  }

  public static class Stack {
    private final Deque<String> crates = new LinkedList<>();

    private final int startIndex;
    private final int endIndex;

    public Stack(int startIndex, int endIndex) {
      this.startIndex = startIndex;
      this.endIndex = endIndex;
    }

    public void parseCrate(String line) {
      String crate = line.substring(startIndex, endIndex);
      if (!crate.isBlank()) {
        crates.add(crate);
      }
    }

    public String peekTopCrate() {
      return crates.peekFirst();
    }

    public String pollTopCrate() {
      return crates.pollFirst();
    }

    public void addTopCrate(String crate) {
      crates.addFirst(crate);
    }
  }
}
