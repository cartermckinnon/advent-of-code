package year2022.day8;

public class Extension {
  public static void main(String[] args) throws Exception {
    int[][] map = Common.loadMap(args[0]);
    int[][] scenicScores = new int[map.length][map.length];
    int maxScenicScore = 1;
    for (int row = 1; row < map.length - 1; row++) {
      for (int col = 1; col < map.length - 1; col++) {
        int height = map[row][col];
        int north = 1, south = 1, east = 1, west = 1;
        for (; row - north > 0 && map[row - north][col] < height; north++)
          ;
        for (; row + south < map.length - 1 && map[row + south][col] < height; south++)
          ;
        for (; col + east < map.length - 1 && map[row][col + east] < height; east++)
          ;
        for (; col - west > 0 && map[row][col - west] < height; west++)
          ;
        int scenicScore = north * south * east * west;
        scenicScores[row][col] = scenicScore;
        maxScenicScore = Math.max(maxScenicScore, scenicScore);
      }
    }
    // Common.printMatrix(scenicScores, " ");
    System.out.println(maxScenicScore);
  }
}
