package aoc.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.StringJoiner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkUtils {

  private static final String SESSION_KEY = "53616c7465645f5f15abcaeb658d22e70b08c76656d9dcf1a1b12a9b03fedea1b87d8b53b6f684bfc1bf6d310c6834bb077bfc1b904cf60209a4c89c1bf75ac6";
  private static final Connection CONNECTION = Jsoup.newSession()
      .cookie("session", SESSION_KEY);

  public static String getInput(Class<?> clz) {
    try {
      return CONNECTION.url(LinkUtils.getUrl(clz)).get().body().html();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  private static String getUrl(Class<?> clz) {
    String name = clz.getName();
    StringJoiner joiner = new StringJoiner("/");
    joiner.add("https://adventofcode.com");
    joiner.add(name.substring(1, 5));
    joiner.add("day");
    joiner.add(String.valueOf(Integer.parseInt(name.substring(7, 9))));
    joiner.add("input");
    return joiner.toString();
  }
}
