package y2021;

import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.LinkUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class D04GiantSquid {

  // number of row/col
  private static final int DIM = 5;
  private static final int BOARD_SIZE = DIM * DIM;
  // track highlighted each row/col on boards
  private static final int TRACK_SIZE = 2 * DIM;
  private static final List<Integer> GUESSES;
  // flat boards
  private static final List<Integer> BOARDS;
  private static final int NUM_BOARDS;
  // mem board position of each value
  private static final Map<Integer, List<Integer>> VALUE_MAP;

  static {
    String raw = LinkUtils.getInput(D04GiantSquid.class);
    String[] ss = raw.split("\s+", 2);
    GUESSES = List.of(ss[0].split(","))
        .map(Integer::parseInt);
    BOARDS = List.of(ss[1].split(" "))
        .map(Integer::parseInt);
    NUM_BOARDS = BOARDS.length() / BOARD_SIZE;
    VALUE_MAP = BOARDS
        .zipWithIndex()
        .groupBy(Tuple2::_1)
        .mapValues(l -> l.map(Tuple2::_2));
  }

  public static int q1() {
    return getScore(Tuple.of(List.fill(NUM_BOARDS * TRACK_SIZE, 0)),
        (a, i) -> a
            .map(ls -> ls.update(i, n -> n + 1)),
        (a, i) -> a._1.get(i) == DIM);
  }

  public static int q2() {
    return getScore(Tuple.of(List.fill(NUM_BOARDS * TRACK_SIZE, 0), List.range(0, NUM_BOARDS).toSet()),
        (a, i) -> a
            .map1(ls -> ls.update(i, n -> n + 1))
            .map((ls, c) -> Tuple.of(ls, ls.get(i) == DIM ? c.remove(i / TRACK_SIZE) : c)),
        (a, i) -> a._2.isEmpty());
  }

  public static <T> int getScore(T acc,
                                 Function2<T, Integer, T> updater,
                                 Function2<T, Integer, Boolean> finder) {
    return GUESSES.toStream()
        .zipWithIndex()
        .scanLeft(Tuple.of(Option.<Tuple2<Integer, Integer>>none(), acc),
            (t, gi) -> t.apply((o, l) -> o.isDefined() ? t
                : VALUE_MAP.get(gi._1).get()
                .flatMap(p -> Tuple.of(p / BOARD_SIZE, p % BOARD_SIZE)
                    .apply((b, x) -> List.of(TRACK_SIZE * b + x / DIM, TRACK_SIZE * b + DIM + x % DIM)))
                .foldLeft(t, (p, i) -> p._1.isDefined() ? p : p
                    .map2(a -> updater.apply(a, i))
                    .map((opt, a) -> Tuple.of(finder.apply(a, i) ? Option.of(Tuple.of(gi._2, i / TRACK_SIZE)) : opt, a))
                )))
        .find(p -> p._1.isDefined())
        .flatMap(Tuple2::_1).get()
        .apply((i, b) -> GUESSES.get(i) * BOARDS
            .subSequence(BOARD_SIZE * b, BOARD_SIZE * (b + 1))
            .filterNot(GUESSES.take(i + 1).toSet()::contains)
            .sum().intValue());
  }
}
