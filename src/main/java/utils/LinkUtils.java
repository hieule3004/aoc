package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.util.StringJoiner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkUtils {

  private static final String SESSION_KEY = "53616c7465645f5fd474fe2c51deb94e08915c4d0305147226076793d2a9d92b9685def23d0d700611a652fad8a58d66";
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
