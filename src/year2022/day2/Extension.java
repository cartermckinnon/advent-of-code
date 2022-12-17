package year2022.day2;

import java.io.File;
import java.util.Scanner;

/**
 * @author carter
 */
public class Extension {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("usage: Extension DATA_FILE");
      System.exit(1);
    }
    int score = 0;
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      while (scanner.hasNextLine()) {
        String[] codes = scanner.nextLine().split(" ");
        Move opponent = Move.fromCode(codes[0]);
        Outcome desiredOutcome = Outcome.fromCode(codes[1]);
        Move myMove = getMoveForDesiredOutcome(opponent, desiredOutcome);
        score += myMove.pointValue + desiredOutcome.pointValue;
      }
    }
    System.out.println(score);
  }

  public static Move getMoveForDesiredOutcome(Move opponent, Outcome desiredOutcome) {
    int delta = 0;
    if (Outcome.WIN == desiredOutcome) {
      delta = opponent == Move.SCISSORS ? -2 : 1;
    } else if (Outcome.LOSE == desiredOutcome) {
      delta = opponent == Move.ROCK ? 2 : -1;
    }
    return Move.values()[opponent.ordinal() + delta];
  }
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
      case "A":
        return Move.ROCK;
      case "B":
        return Move.PAPER;
      case "C":
        return Move.SCISSORS;
      default:
        throw new UnsupportedOperationException("unknown move: " + code);
    }
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

  public static Outcome fromCode(String code) {
    switch (code) {
      case "X":
        return Outcome.LOSE;
      case "Y":
        return Outcome.DRAW;
      case "Z":
        return Outcome.WIN;
      default:
        throw new UnsupportedOperationException("unknown outcome: " + code);
    }
  }
}
