package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamUtils {

  public static <T> T concurrentExceptionCombiner(T t1, T t2) {
    throw new UnsupportedOperationException();
  }
}
