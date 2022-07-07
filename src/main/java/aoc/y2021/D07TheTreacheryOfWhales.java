package aoc.y2021;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import aoc.utils.DailyChallenge;

public class D07TheTreacheryOfWhales implements DailyChallenge {

  private static final List<Tuple2<Integer, Integer>> INPUT = List
//      .of(LinkUtils.getInput(D07TheTreacheryOfWhales.class).split(","))
//      .map(Integer::parseInt)
      .of(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
      .toSortedMap(Function1.identity(), Function1.constant(1), Integer::sum)
      .toList();

  @Override
  public Number q1() {
    return INPUT.tail()
        .scanLeft(Tuple.of(INPUT.head(),
                0, INPUT.tail().map(Tuple2::_2).sum().intValue(),
                0, INPUT.tail().map(e -> e._2 * (e._1 - INPUT.head()._1)).sum().intValue()),
            (t, e) -> t.apply((p, cl, cr, sl, sr) -> Tuple.of(e._1 - p._1, cl + p._2)
                .apply((d, ncl) -> Tuple.of(e, ncl, cr - e._2, sl + ncl * d, sr - cr * d))))
        .map(t -> t.apply((p, cl, cr, sl, sr) -> sl + sr))
        .min().get();
  }

  @Override
  public Number q2() {
    return INPUT.tail()
        .scanLeft(Tuple.of(INPUT.head(),
                0, INPUT.tail().map(Tuple2::_2).sum().intValue(),
                0, INPUT.tail().map(e -> e
                    .map1(v -> v - INPUT.head()._1).map1(v -> v * (v - 1) / 2)
                    .apply(Math::multiplyExact))
                    .sum().intValue()),
            (t, e) -> t.apply((p, cl, cr, sl, sr) -> Tuple.of(e._1 - p._1, cl + p._2)
                .apply((d, ncl) -> Tuple.of(e, ncl, cr - e._2, sl + ncl * d, sr - cr * d))))
        .map(t -> t.apply((p, cl, cr, sl, sr) -> sl + sr))
        .min().get();
//    return List.range(0, INPUT.length())
//        .foldLeft(Integer.MAX_VALUE, (m, i) -> Option.of(INPUT.removeAt(i)
//                .map(e -> e._2 * Option.of(Math.abs(e._1 - INPUT.get(i)._1))
////                    .map(n -> n * (n + 1) / 2)
//                    .get())
//                .sum().intValue())
//            .map(v -> Math.min(m, v))
//            .get());
  }
}
