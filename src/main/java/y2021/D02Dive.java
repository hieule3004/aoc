package y2021;

import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import utils.LinkUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class D02Dive {

  private static final List<Tuple2<Command, Integer>> INPUT = List
      .of(LinkUtils.getInput(D02Dive.class).split(" (?=[fud])"))
      .map(raw -> Option.of(raw.split("\s+"))
          .toMap(ss -> Command.fromName(ss[0]), ss -> Integer.parseInt(ss[1]))
          .head());

  public static int q1() {
    return INPUT
        .foldLeft(Tuple.of(0, 0),
            (t, c) -> c._1.op1.apply(t, c._2))
        .apply(Math::multiplyExact);
  }

  public static int q2() {
    return INPUT
        .foldLeft(Tuple.of(0, 0, 0),
            (t, c) -> c._1.op2.apply(t, c._2))
        .remove3()
        .apply(Math::multiplyExact);
  }

  @AllArgsConstructor
  private enum Command {
    FORWARD((t, v) -> Tuple.of(t._1 + v, t._2), (t, v) -> Tuple.of(t._1 + v, t._2 + t._3 * v, t._3)),
    UP((t, v) -> Tuple.of(t._1, t._2 - v), (t, v) -> Tuple.of(t._1, t._2, t._3 - v)),
    DOWN((t, v) -> Tuple.of(t._1, t._2 + v), (t, v) -> Tuple.of(t._1, t._2, t._3 + v)),
    ;

    private final Function2<Tuple2<Integer, Integer>, Integer, Tuple2<Integer, Integer>> op1;
    private final Function2<Tuple3<Integer, Integer, Integer>, Integer, Tuple3<Integer, Integer, Integer>> op2;

    private static final Map<String, Command> MAP = Stream.of(Command.values())
        .toMap(c -> c.name().toLowerCase(), Function1.identity());

    private static Command fromName(String name) {
      return MAP.get(name).get();
    }
  }
}
