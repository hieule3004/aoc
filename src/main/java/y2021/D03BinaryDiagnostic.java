package y2021;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.LinkUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class D03BinaryDiagnostic {

  private static final List<String> INPUT = List
      .of(LinkUtils.getInput(D03BinaryDiagnostic.class).split(" "));

  public static int q1() {
    return INPUT
        .map(s -> List.ofAll(s.chars().boxed())
            .map(c -> c > '0' ? 1 : -1))
        .reduce((s1, s2) -> s1.zipWith(s2, Math::addExact))
        .map(v -> v > 0 ? 1 : 0)
        .reduceOption((s, v) -> 2 * s + v)
        .map(g -> g * ((1 << INPUT.head().length()) - 1 - g))
        .get();
  }

  public static int q2() {
    return getCommon('1') * getCommon('0');
  }

  private static int getCommon(char c) {
    return List.range(0, INPUT.head().length())
        .foldLeft(INPUT, (l, i) -> l.length() == 1 ? l : Option
            .of(l.groupBy(v -> v.charAt(i)))
            .map(g -> g
                .get(g.get('1').get().length() < g.get('0').get().length()
                    ? (char) ('0' + '1' - c) : c)
                .get())
            .get())
        .headOption()
        .map(s -> s.chars().reduce(0, (a, v) -> 2 * a + (v - '0')))
        .get();
  }
}
