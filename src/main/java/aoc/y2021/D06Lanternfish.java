package aoc.y2021;

import io.vavr.collection.List;
import aoc.utils.DailyChallenge;
import aoc.utils.LinkUtils;

public class D06Lanternfish implements DailyChallenge {

  private static final List<Long> INPUT = List
      .of(LinkUtils.getInput(D06Lanternfish.class).split(","))
      .map(Integer::parseInt)
      .foldLeft(List.fill(9, 0L),
          (ls, i) -> ls.update(i, v -> v + 1));

  @Override
  public Number q1() {
    return iterateDays(80);
  }

  @Override
  public Number q2() {
    return iterateDays(256);
  }

  private Number iterateDays(int n) {
    return List.range(0, n)
        .foldLeft(INPUT,
            (a, u) -> a.tail()
                .update(6, i -> i + a.head())
                .append(a.head()))
        .sum();
  }
}
