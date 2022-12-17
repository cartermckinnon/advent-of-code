package year2021.day14;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.err.println("usage: Solution INPUT_FILE ITERATIONS");
      System.exit(1);
    }

    String inputFile = args[0];
    int iterations = Integer.parseInt(args[1]);
    List<String> lines = Files.readAllLines(new File(inputFile).toPath());
    String polymerTemplate = lines.get(0);
    System.out.println(polymerTemplate);

    List<String> ruleLines = lines.subList(2, lines.size());
    Map<String, String> rules = loadRules(ruleLines);
    System.out.println(rules);

    generatePolynomial(polymerTemplate, rules, iterations);
  }

  public static Map<String, String> loadRules(List<String> lines) {
    Map<String, String> rules = new HashMap<>();
    for (String line : lines) {
      String[] parts = line.split(" \\-\\> ");
      rules.put(parts[0], parts[1]);
    }
    return rules;
  }

  public static void generatePolynomial(
      String template, Map<String, String> rules, int iterations) {
    Map<String, Long> currentPolynomial = new TreeMap<>();
    Map<String, Long> subsequentPolynomial = new TreeMap<>();
    // initial polymer template
    for (int i = 0; i < template.length() - 1; i++) {
      currentPolynomial.compute(
          template.substring(i, i + 2), (key, count) -> count == null ? 1 : count + 1);
    }
    System.out.println("initialPolynomial=" + currentPolynomial);
    Map<Character, Long> charCounts = new HashMap<>();
    for (char c : template.toCharArray()) {
      charCounts.compute(c, (k, v) -> v == null ? 1 : v + 1);
    }
    System.out.println("initialCharCounts=" + charCounts);
    // generate the polynomial
    for (int i = 0; i < iterations; i++) {
      System.out.println(i + "=" + currentPolynomial);
      for (Map.Entry<String, Long> pair : currentPolynomial.entrySet()) {
        String rule = rules.get(pair.getKey());
        if (rule != null) {
          subsequentPolynomial.compute(
              pair.getKey().charAt(0) + rule, (key, val) -> increment(val, pair.getValue()));
          subsequentPolynomial.compute(
              rule + pair.getKey().charAt(1), (key, val) -> increment(val, pair.getValue()));
          charCounts.compute(
              rule.charAt(0), (k, v) -> v == null ? pair.getValue() : v + pair.getValue());
        } else {
          subsequentPolynomial.compute(pair.getKey(), (key, val) -> increment(val, 1L));
        }
      }
      currentPolynomial = subsequentPolynomial;
      subsequentPolynomial = new TreeMap<>();
    }
    System.out.println("finalPolynomial=" + currentPolynomial);
    System.out.println("finalCharCounts=" + charCounts);
    Long min = Long.MAX_VALUE;
    Long max = Long.MIN_VALUE;
    for (Map.Entry<Character, Long> entry : charCounts.entrySet()) {
      // long val = (entry.getValue() + 1) / 2;
      long val = entry.getValue();
      if (val < min) {
        min = val;
      }
      if (val > max) {
        max = val;
      }
    }
    System.out.println("min=" + min);
    System.out.println("max=" + max);
    System.out.println("delta=" + (max - min));
  }

  private static Long increment(Long val, Long amount) {
    return val == null ? amount : val + amount;
  }
}
