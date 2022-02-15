package y2021;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.LinkUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class D05HydrothermalVenture {

  private static final List<Tuple2<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>>> INPUT = List
      .of(LinkUtils.getInput(D05HydrothermalVenture.class).split(" "))
      .grouped(3)
      .map(D05HydrothermalVenture::getPair)
      .toList();

  public static int q1() {
    return 0;
  }

  public static int q2() {
    return 0;
  }

  private static Tuple2<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>> getPair(List<String> ls) {
    return Option.of(ls
            .filterNot("-&gt;"::equals)
            .map(raw -> List.of(raw.split(","))
                .map(Integer::parseInt))
            .map(ss -> Tuple.of(ss.get(0), ss.get(1))))
        .map(ts -> Tuple.of(ts.get(0), ts.get(1)))
        .get();
  }


}
