package y2021;

import io.vavr.collection.List;
import utils.DailyChallenge;
import utils.LinkUtils;

public class D06Lanternfish implements DailyChallenge {

  private static final List<Integer> INPUT = List
      .of(LinkUtils.getInput(D06Lanternfish.class).split(","))
      .map(Integer::parseInt);

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
        .foldLeft(INPUT
                .foldLeft(List.fill(9, 0L),
                    (ls, i) -> ls.update(i, v -> v + 1)),
            (a, u) -> a.tail()
                .update(6, i -> i + a.head())
                .append(a.head()))
        .sum();
  }
}
