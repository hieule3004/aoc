package aoc.y2021;

import io.vavr.Tuple;
import io.vavr.collection.List;
import aoc.utils.DailyChallenge;
import aoc.utils.LinkUtils;

public class D01SonarSweep implements DailyChallenge {

  private static final List<Integer> INPUT = List
      .of(LinkUtils.getInput(D01SonarSweep.class).split(" "))
      .map(Integer::parseInt);

  @Override
  public Number q1() {
    return INPUT.tail()
        .foldLeft(Tuple.of(INPUT.head(), 0),
            (a, v) -> Tuple.of(v, a._2 + (a._1 < v ? 1 : 0)))
        ._2;
  }

  @Override
  public Number q2() {
    return INPUT
        .splitAt(3)
        .apply((p, l) -> l
            .foldLeft(Tuple.of(p, 0),
                (a, v) -> Tuple.of(a._1.tail().append(v), a._2 + (a._1.head() < v ? 1 : 0)))
            ._2);
  }
}
