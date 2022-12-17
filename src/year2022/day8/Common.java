package year2022.day8;

import java.io.File;
import java.util.Scanner;

public class Common {
  public static int[][] loadMap(String file) throws Exception {
    Scanner scanner = new Scanner(new File(file));
    int[][] map = null;
    int row = 0;
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      if (map == null) {
        map = new int[line.length()][line.length()];
      }
      for (int col = 0; col < line.length(); col++) {
        map[row][col] = Character.getNumericValue(line.charAt(col));
      }
      row++;
    }
    return map;
  }

  public static void printMatrix(int[][] matrix, String delimiter) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        if (j > 0) {
          System.out.print(delimiter);
        }
        System.out.print(matrix[i][j]);
      }
      System.out.print("\n");
    }
  }
}
