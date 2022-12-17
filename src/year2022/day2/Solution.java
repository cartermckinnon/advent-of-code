package year2022.day2;

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
    int score = 0;
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      while (scanner.hasNextLine()) {
        String[] moves = scanner.nextLine().split(" ");
        Move opponent = Move.fromCode(moves[0]);
        Move me = Move.fromCode(moves[1]);
        score += me.determineOutcome(opponent).pointValue + me.pointValue;
      }
    }
    System.out.println(score);
  }

  enum Move {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    final int pointValue;

    Move(int pointValue) {
      this.pointValue = pointValue;
    }

    public static Move fromCode(String code) {
      switch (code) {
        case "A", "X":
          return Move.ROCK;
        case "B", "Y":
          return Move.PAPER;
        case "C", "Z":
          return Move.SCISSORS;
        default:
          throw new UnsupportedOperationException("unknown move: " + code);
      }
    }

    public Outcome determineOutcome(Move opponent) {
      if (opponent == this) {
        return Outcome.DRAW;
      } else if (opponent.ordinal() == this.ordinal() + 1) {
        return Outcome.LOSE;
      } else if (opponent.ordinal() == this.ordinal() - 2) {
        return Outcome.LOSE;
      }
      return Outcome.WIN;
    }
  }

  enum Outcome {
    WIN(6),
    LOSE(0),
    DRAW(3);

    final int pointValue;

    Outcome(int pointValue) {
      this.pointValue = pointValue;
    }
  }
}
