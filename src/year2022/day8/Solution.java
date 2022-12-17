package year2022.day8;

public class Solution {
  public static void main(String[] args) throws Exception {
    int[][] map = Common.loadMap(args[0]);
    int[][] visibility = new int[map.length][map.length];
    for (int row = 0; row < map.length; row++) {
      int westerlyTreeLine = map[row][0];
      for (int col = 1; col < map.length - 1; col++) {
        if (map[row][col] <= westerlyTreeLine) {
          // not visible from the west
          visibility[row][col] += 1;
        } else {
          // visible from the west
          westerlyTreeLine = map[row][col];
        }
      }
      int easterlyTreeLine = map[row][map.length - 1];
      for (int col = map.length - 2; col > 0; col--) {
        if (map[row][col] <= easterlyTreeLine) {
          // not visible from the east
          visibility[row][col] += 1;
        } else {
          // visible from the east
          easterlyTreeLine = map[row][col];
        }
      }
    }
    for (int col = 0; col < map.length; col++) {
      int northlyTreeLine = map[0][col];
      for (int row = 1; row < map.length - 1; row++) {
        if (map[row][col] <= northlyTreeLine) {
          // not visible from the north
          visibility[row][col] += 1;
        } else {
          // visible from the north
          northlyTreeLine = map[row][col];
        }
      }
      int southlyTreeLine = map[map.length - 1][col];
      for (int row = map.length - 2; row > 0; row--) {
        if (map[row][col] <= southlyTreeLine) {
          // not visible from the south
          visibility[row][col] += 1;
        } else {
          // visible from the south
          southlyTreeLine = map[row][col];
        }
      }
    }
    // Common.printMatrix(visibility, "");
    int visible = countVisible(visibility);
    System.out.println(visible);
  }

  public static int countVisible(int[][] visibility) {
    int visible = 0;
    for (int row = 0; row < visibility.length; row++) {
      for (int col = 0; col < visibility.length; col++) {
        visible += visibility[row][col] < 4 ? 1 : 0;
      }
    }
    return visible;
  }
}
