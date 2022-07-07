package aoc.y2021;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;
import aoc.utils.DailyChallenge;
import aoc.utils.LinkUtils;

public class D05HydrothermalVenture implements DailyChallenge {

  private static final List<Tuple2<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>>> INPUT = List
      .of(LinkUtils.getInput(D05HydrothermalVenture.class).split(" "))
      .grouped(3)
      .map(D05HydrothermalVenture::getPair)
      .toList();

  private static Tuple2<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>> getPair(List<String> ls) {
    return Option.of(ls
            .filterNot("-&gt;"::equals)
            .map(raw -> List.of(raw.split(","))
                .map(Integer::parseInt))
            .map(ss -> Tuple.of(ss.get(0), ss.get(1))))
        .map(ts -> Tuple.of(ts.get(0), ts.get(1)))
        .get();
  }

  @Override
  public Number q1() {
    return getPoints(ts -> List.empty());
  }

  @Override
  public Number q2() {
    return getPoints(ts -> iterateRangeInclusive(ts._1._1, ts._2._1)
        .zip(iterateRangeInclusive(ts._1._2, ts._2._2)));
  }

  private static int getPoints(Function1<Tuple2<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>>, List<Tuple2<Integer, Integer>>> supplier) {
    return INPUT
        .flatMap(ts -> (ts._1._1 - ts._2._1) * (ts._1._2 - ts._2._2) == 0
            ? ts.apply((a, b) -> a._1.equals(b._1)
            ? repeatRangeInclusive(a._2, b._2, a._1)
            .zip(iterateRangeInclusive(a._2, b._2))
            : iterateRangeInclusive(a._1, b._1)
            .zip(repeatRangeInclusive(a._1, b._1, a._2)))
            : supplier.apply(ts))
        .groupBy(Function1.identity())
        .count(t -> t._2.size() > 1);
  }

  private static List<Integer> iterateRangeInclusive(int x, int y) {
    return List.rangeClosedBy(x, y, Integer.compare(y, x));
  }

  private static List<Integer> repeatRangeInclusive(int x, int y, int v) {
    return List.fill(Math.abs(x - y) + 1, v);
  }
}
