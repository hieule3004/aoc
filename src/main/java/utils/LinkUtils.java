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

  private static final String SESSION_KEY = "53616c7465645f5f55d440c7a77c8f31e74336b09245af084709f0ea4cfda3752931e74dafdf34c4cbba2d73c3bc4d9979acc94e378137dc0c7b13929796b091";
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
