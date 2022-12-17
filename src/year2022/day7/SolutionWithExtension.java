package year2022.day7;

import java.io.File;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class SolutionWithExtension {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(new File(args[0]));
    Map<String, Integer> directorySizes = new HashMap<>();
    Deque<String> curDir = new LinkedList<>();
    curDir.add("root");
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split(" ");
      if ("$".equals(parts[0])) {
        if ("cd".equals(parts[1])) {
          switch (parts[2]) {
            case "..":
              curDir.removeLast();
              break;
            case "/":
              curDir.clear();
              curDir.add("root");
              break;
            default:
              curDir.addLast(parts[2]);
          }
        }
      } else if (!"dir".equals(parts[0])) {
        StringBuilder path = new StringBuilder();
        int fileSize = Integer.parseInt(parts[0]);
        for (String dir : curDir) {
          path.append("/").append(dir);
          directorySizes.compute(
              path.toString(), (ignored, total) -> (total == null ? 0 : total) + fileSize);
        }
      }
    }

    int totalSpace = 70_000_000;
    int freeSpaceNeededForUpdate = 30_000_000;
    int usedSpace = directorySizes.get("/root");
    int freeSpace = totalSpace - usedSpace;
    int spaceToFree = freeSpaceNeededForUpdate - freeSpace;

    int usedSpaceUnderCertainDirectorySize = 0;
    int certainDirectorySize = 100_000;
    int sizeOfDirectoryToDelete = 0;
    int minDeltaBetweenNeededSpaceAndDirectorySize = Integer.MAX_VALUE;
    for (Integer directorySize : directorySizes.values()) {
      if (directorySize <= certainDirectorySize) {
        usedSpaceUnderCertainDirectorySize += directorySize;
      }
      if (directorySize >= spaceToFree) {
        int delta = directorySize - spaceToFree;
        if (delta < minDeltaBetweenNeededSpaceAndDirectorySize) {
          minDeltaBetweenNeededSpaceAndDirectorySize = delta;
          sizeOfDirectoryToDelete = directorySize;
        }
      }
    }
    System.out.println(usedSpaceUnderCertainDirectorySize);
    System.out.println(sizeOfDirectoryToDelete); // extension
  }
}
